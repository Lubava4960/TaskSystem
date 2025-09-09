package com.example.controller;

import com.example.dto.TaskDto;

import com.example.service.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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
    @Operation(
            summary = "можно просмотреть задачи ",
            tags = "Задачи"
    )
    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        List<TaskDto> tasks = taskService.findAll();
        log.info("tasks: {}", tasks);
        return (tasks);
    }
    @Operation(
            summary = "можно обновить данные по задаче ",
            description = "введите id задачи ",
            tags = "Задачи"
    )
    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable("id") Integer id, @RequestBody TaskDto task) {
        task.setId(id);
        return taskService.updateTask(task);

    }
    @Operation(
            summary = "можно получить задачи по id исполнителя ",
            description = "введите id исполнителя ",
            tags = "Задачи"
    )
    @GetMapping("/user/{userId}")
    public Page<TaskDto> getTasksByUserId(@PathVariable("userId") UUID userId,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getTasksByUserId(userId, pageable);
    }
    @Operation(
            summary = "можно получить задачи по фамилии исполнителя ",
            description = "введите id исполнителя ",
            tags = "Задачи"
    )
    @GetMapping("/search")
    public Page<TaskDto> getTasksByUserLastName(@RequestParam String lastName,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getTasksByUserLastName(lastName, pageable);
    }
}
