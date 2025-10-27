package com.example.dao.task;

import com.example.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class TaskDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_TASK = """
            
                      WITH new_task AS (
                    INSERT INTO public.task (
            
                        title,
                        description,
                        user_id,
                        "create",
                        "update",
                        comment,
                        status,
                        priority
                    ) VALUES (?,?, ?::uuid, NOW(), NOW(), ?,?,?)
                    RETURNING id
                )
                INSERT INTO public.user_tasks (user_id, task_id)
                VALUES (?, (SELECT id FROM new_task))
            """;

    public void addTask(TaskDto taskDto) {
        jdbcTemplate.update(INSERT_TASK,

                taskDto.getTitle(),
                taskDto.getDescription(),
                taskDto.getUserId(),
                taskDto.getComment(),

                taskDto.getStatus().getLabel(),
                taskDto.getPriority().getValue(),
                 taskDto.getUserId());


    }

    private static final String DELETE_TASK_QUERY = """
            DELETE FROM public.task
            WHERE id = ?
            """;

    public void deleteTask(Integer taskId) {
        int rowsAffected = jdbcTemplate.update(DELETE_TASK_QUERY, taskId);

        if (rowsAffected == 0) {
            log.info("Задача с ID {} не найдена.", taskId);
        } else {
            log.info("Задача с ID {} успешно удалена.", taskId);
        }

    }

    private final String SELECT_TASK = "SELECT * FROM public.task";

    public List<TaskDto> selectTask() {
        return jdbcTemplate.query(SELECT_TASK, new TaskRowMapper());
    }


    private static class TaskRowMapper implements RowMapper<TaskDto> {
        @Override
        public TaskDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaskDto task = new TaskDto();
            task.setId(rs.getInt("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setUserId(java.util.UUID.fromString((rs.getString("user_id"))));
            task.setComment(rs.getString("comment"));

            return task;
        }
    }

    public void updateTask(TaskDto task) {
        String updateQuery = "UPDATE public.task " +
                "SET title = ?," +
                " description = ?," +
                " user_id = ?::uuid," +
                " comment = ?" +
                " WHERE id = ?";
        jdbcTemplate.update(updateQuery,
                task.getTitle(),
                task.getDescription(),
                task.getUserId().toString(),
                task.getComment(),
                task.getId());
    }
    public List<TaskDto> findTasksByUserId(UUID userId, int limit, int offset) {
        String sql = "SELECT * FROM public.task WHERE user_id = ? ORDER BY id LIMIT ? OFFSET ?";
        int totalTasks = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM public.task WHERE user_id = ?", Integer.class, userId);

        List<TaskDto> tasks = jdbcTemplate.query(
                sql,
                new Object[]{userId, limit,  offset},
                new TaskRowMapper());

        return  (tasks);
    }
    public Page<TaskDto> findTasksByUserLastName(String lastName, Pageable pageable) {

        String sql = "SELECT t.* FROM public.task t " +
                "JOIN public.user u ON t.user_id = u.id " +
                "WHERE u.last_name = ? " +
                "ORDER BY t.id LIMIT ? OFFSET ?";


        int totalTasks = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM public.task t " +
                        "JOIN public.user u ON t.user_id = u.id " +
                        "WHERE u.last_name = ?",
                Integer.class, lastName);


        List<TaskDto> tasks = jdbcTemplate.query(
                sql,
                new Object[]{lastName, pageable.getPageSize(), pageable.getOffset()},
                new TaskRowMapper());


        return new PageImpl<>(tasks, pageable, totalTasks);
    }
}







