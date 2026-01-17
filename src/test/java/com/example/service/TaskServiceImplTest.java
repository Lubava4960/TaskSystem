package com.example.service;

import com.example.dao.task.TaskDao;
import com.example.dto.TaskDto;
import com.example.enumeration.TaskPriority;
import com.example.enumeration.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.example.enumeration.TaskPriority.HIGH;
import static com.example.enumeration.TaskPriority.MEDIUM;
import static com.example.enumeration.TaskStatus.PENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {
    @Mock
    private TaskDao taskDao;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1);
        taskDto.setTitle("Test Task");
        taskDto.setDescription("Test Description");
        taskDto.setUserId(UUID.randomUUID());
        taskDto.setComment("Test comment");
        taskDto.setPriority( HIGH);
        taskDto.setStatus(PENDING);

        taskService.save(taskDto);
        System.out.println(taskDto);

        verify(taskDao, times(1)).addTask(taskDto);
    }

    @Test
    void delete() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1);
        taskDto.setTitle("Test Task");
        taskDto.setDescription("Test Description");
        taskDto.setUserId(UUID.randomUUID());
        taskDto.setComment("Test comment");
        taskDto.setPriority( HIGH);
        taskDto.setStatus(PENDING);
        taskService.delete(taskDto);
        verify(taskDao, times(1)).deleteTask(taskDto.getId());
    }

    @Test
    void findAll() {
        TaskDto task1 = new TaskDto();
        task1.setId(1);
        task1.setTitle("Test Task");
        task1.setDescription("Test Description");
        task1.setUserId(UUID.randomUUID());
        task1.setComment("Test comment");
        task1.setPriority(HIGH);
        task1.setStatus(PENDING);
        taskService.save(task1);
        TaskDto task2 = new TaskDto();
        task2.setId(2);
        task2.setTitle("Test Task2");
        task2.setDescription("Test Description2");
        task2.setUserId(UUID.randomUUID());
        task2.setComment("Test comment2");
        task2.setPriority(MEDIUM);
        task2.setStatus(PENDING);
        taskService.save(task2);
        List<TaskDto> tasks = Arrays.asList(task1, task2);
        when(taskService.findAll()).thenReturn(tasks);
        System.out.println(tasks);
    }

    @Test
    void updateTask() {
        TaskDto task3 = new TaskDto( 5,"Task","Description", UUID.randomUUID(),
                        "comment", TaskStatus.PENDING, TaskPriority.LOW);

        TaskDto result = taskService.updateTask(task3);

        assertNotNull(result); // Проверка, что результат не null
        assertEquals(task3.getId(), result.getId()); // Проверка ID
        assertEquals(task3.getTitle(), result.getTitle()); // Проверка имени

        verify(taskDao, times(1)).updateTask(task3);
    }

    @Test
    void getTasksByUserId() {
        UUID userId = UUID.randomUUID();
        List<TaskDto> expectedTasks = Arrays.asList(
                new TaskDto(1, "Task One", "Description One", userId, "Comment One", TaskStatus.PENDING, TaskPriority.LOW),
                new TaskDto(2, "Task Two", "Description Two",userId, "Comment Two", TaskStatus.COMPLETED, TaskPriority.HIGH)
        );

        int limit = 2;
        int offset = 0;

        when(taskDao.findTasksByUserId(userId, limit, offset)).thenReturn(expectedTasks);

        List<TaskDto> result = taskService.getTasksByUserId(userId, limit, offset);


        assertNotNull(result); // Проверка, что результат не null
        assertEquals(expectedTasks.size(), result.size()); // Проверка размера списка
        assertEquals(expectedTasks, result); // Проверка содержимого списка


        verify(taskDao, times(1)).findTasksByUserId(userId, limit, offset);
    }

    @Test
    void getTasksByUserLastName() {
        String lastName = "Doe";
        Pageable pageable = Pageable.unpaged();
        List<TaskDto> tasks = Arrays.asList(
                new TaskDto(1, "Task One", "Description One", null, "Comment One", TaskStatus.PENDING, TaskPriority.LOW),
                new TaskDto(2, "Task Two", "Description Two", null, "Comment Two", TaskStatus.COMPLETED, TaskPriority.HIGH)
        );
        Page<TaskDto> expectedPage = new PageImpl<>(tasks); // Создание страницы с задачами

        when(taskDao.findTasksByUserLastName(lastName, pageable)).thenReturn(expectedPage);

        Page<TaskDto> result = taskService.getTasksByUserLastName(lastName, pageable);

        assertNotNull(result);
        assertEquals(expectedPage.getContent().size(), result.getContent().size()); // Проверка размера содержимого страницы
        assertEquals(expectedPage.getContent(), result.getContent()); // Проверка содержимого страниц


        verify(taskDao, times(1)).findTasksByUserLastName(lastName, pageable);
    }

}