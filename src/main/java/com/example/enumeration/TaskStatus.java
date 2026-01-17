package com.example.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {

    PENDING("в ожидании"),
    IN_PROGRESS("в процессе"),
    COMPLETED("завершено");

    private final String label;
}
