package com.example.controller;

import com.example.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @GetMapping("/me")
    public UserDto getMe() {
        return new UserDto();
    }
}
