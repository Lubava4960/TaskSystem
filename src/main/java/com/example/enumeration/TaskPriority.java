package com.example.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskPriority {
    HIGH("высокий"),
    MEDIUM("средний"),
    LOW("низкий");

    private final String value;
}
