package com.example.dto;

import com.example.enumeration.TaskPriority;
import com.example.enumeration.TaskStatus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TaskDto {

        private int id;

        private String title;

        private String description;

        private UUID userId;

        private String comment;

        private TaskStatus status;

        private TaskPriority priority;



}


