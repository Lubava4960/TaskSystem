package com.example.service;

import com.example.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface TaskService {

    void save(TaskDto taskDto);

    void delete(TaskDto taskDto);

    List<TaskDto> findAll();

    TaskDto updateTask(TaskDto taskDto);


    List<TaskDto> getTasksByUserId(UUID userId, int limit, int offset);

    Page<TaskDto> getTasksByUserLastName(String lastName, Pageable pageable);
}
