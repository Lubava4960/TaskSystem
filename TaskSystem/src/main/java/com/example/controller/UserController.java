package com.example.controller;


import com.example.dto.UserDto;
import com.example.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @Operation(
            summary = "можно создать нового пользователя  ",
            description = "введите данные ",
            tags = "Пользователь"
    )
    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return userDto;
    }
    @Operation(
            summary = "можно просмотреть список пользователей  ",
            tags = "Пользователь"
    )
    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

}

