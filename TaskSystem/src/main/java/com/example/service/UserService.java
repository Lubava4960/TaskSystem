package com.example.service;

import com.example.dto.UserDto;

import java.util.List;

public interface UserService {

    void save(UserDto userDto);

    List<UserDto> findAll();
}
