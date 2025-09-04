package com.example.controller;

import com.example.dto.TaskDto;

import com.example.service.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskServiceImpl taskService;

    @Operation(
            summary = "можно создать  новую задачу ",
            description = "введите данные ",
            tags = "Задачи"
    )
    @PostMapping("/task")
    public TaskDto  createTask(@RequestBody TaskDto taskDto) {
        log.info("taskDto: {}", taskDto);
        taskService.save(taskDto);
        return (taskDto);
    }

}
