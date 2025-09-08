package com.example.service;

import com.example.dao.task.TaskDao;
import com.example.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    @Override
    public void save(TaskDto taskDto) {
        taskDao.addTask(taskDto);

    }

    @Override
    public void delete(TaskDto taskDto) {
        taskDao.deleteTask(taskDto.getId());
    }
}
