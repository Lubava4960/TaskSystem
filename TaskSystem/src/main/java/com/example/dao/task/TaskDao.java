package com.example.dao.task;

import com.example.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;




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
                        comment
                    ) VALUES (?, ?, ?::uuid, NOW(), NOW(), ?)
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
                taskDto.getUserId());
    }
    private static final String DELETE_TASK_QUERY = """
    DELETE FROM public.task
    WHERE id = ?
    """;

    public void deleteTask(Integer taskId) {
        int rowsAffected = jdbcTemplate.update(DELETE_TASK_QUERY, taskId);

        if (rowsAffected == 0) {
            System.out.println("Задача с ID " + taskId + " не найдена.");
        } else {
            System.out.println("Задача с ID " + taskId + " успешно удалена.");
        }
    }
}




