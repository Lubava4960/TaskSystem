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

    Page<TaskDto> getTasksByUserId(UUID userId, Pageable pageable);

    Page<TaskDto> getTasksByUserLastName(String lastName, Pageable pageable);
}
