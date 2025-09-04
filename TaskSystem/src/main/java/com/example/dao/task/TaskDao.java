package com.example.dao.task;

import com.example.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class TaskDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_TASK = """
            
            INSERT INTO public.task (
                   id,
                   title,
                   description,
                   user_id,
                   "create",
                   "update",
                   comment
               ) VALUES (?, ?, ?, ?::uuid, ?, ?,?)

            """;
    public void addTask(TaskDto taskDto) {
            jdbcTemplate.update(INSERT_TASK,
                    taskDto.getId(),
                    taskDto.getTitle(),
                    taskDto.getDescription(),
                    taskDto.getUserId(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    taskDto.getComment());
        }
    }


