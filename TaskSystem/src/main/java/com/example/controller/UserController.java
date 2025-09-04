package com.example.controller;


import com.example.dto.UserDto;
import com.example.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @Operation(
            summary = "можно создать  нового пользователя  ",
            description = "введите данные ",
            tags = "Пользователь"
    )
    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return userDto;
    }

}

