package com.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class TaskDto {
    private int id;
    private String title;
    private String description;
    private UUID userId;
    private String comment;
}
