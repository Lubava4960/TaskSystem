package com.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;


}
