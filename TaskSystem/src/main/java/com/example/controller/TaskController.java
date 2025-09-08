package com.example.controller;

import com.example.dto.TaskDto;

import com.example.service.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        log.info("taskDto: {}", taskDto);
        taskService.save(taskDto);
        return (taskDto);
    }

    @Operation(
            summary = "можно удалить задачу ",
            description = "введите id задачи ",
            tags = "Задачи"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        try {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(id);
            taskService.delete(taskDto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Ошибка при удалении задачи: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
