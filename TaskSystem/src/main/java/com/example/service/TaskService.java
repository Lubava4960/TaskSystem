package com.example.service;

import com.example.dto.TaskDto;


public interface TaskService {

    void save(TaskDto taskDto);

    //    public List<TaskDto> findAll() {
    //        taskDao.
    //    }
    void delete(TaskDto taskDto);
}
