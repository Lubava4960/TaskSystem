package com.example.controller;

import com.example.dto.UserDto;
import com.example.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @Operation(
            summary = "можно удалить пользователя ",
            description = "введите id пользователя ",
            tags = "Пользователь"
    )
    @DeleteMapping("/{id}")
   // @PreAuthorize("hasAuthority('ROLE_admin')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        try {
            UserDto userDto = new UserDto();
            userDto.setId(id);
            userService.deleteUser(userDto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Ошибка при удалении пользователя: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

