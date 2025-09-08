package com.example.service;

import com.example.dto.TaskDto;

import java.util.List;


public interface TaskService {

    void save(TaskDto taskDto);

    void delete(TaskDto taskDto);

    List<TaskDto> findAll();

    TaskDto updateTask(TaskDto taskDto);
}
