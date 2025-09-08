package com.example.service;

import com.example.dao.task.TaskDao;
import com.example.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public List<TaskDto> findAll() {
       return taskDao.selectTask();

    }
    @Override
    public TaskDto updateTask(TaskDto taskDto){
        taskDao.updateTask(taskDto);
        return taskDto;
    }
}
