package com.example.service;

import com.example.dao.user.UserDao;
import com.example.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public void save(UserDto userDto) {
        userDao.addUser(userDto);
    }
}
