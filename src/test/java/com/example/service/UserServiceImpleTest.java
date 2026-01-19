package com.example.service;

import com.example.dao.user.UserDao;
import com.example.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class UserServiceImplTest {
    @Mock
    UserDao userDao;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        UserDto userDto = new UserDto();
        userDto.setId(UUID.randomUUID());
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userServiceImpl.save(userDto);
        System.out.println(userDto);
        verify(userDao, times(1)).addUser(userDto);
    }

    @Test
    void delete() {
        UserDto userDto = new UserDto();
        userDto.setId(UUID.randomUUID());
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userServiceImpl.deleteUser(userDto);
        verify(userDao, times(1)).deleteUser(userDto.getId());
    }

    @Test
    void findAll() {
        UserDto userDto = new UserDto();
        userDto.setId(UUID.randomUUID());
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userServiceImpl.save(userDto);
        UserDto userDto2 = new UserDto();
        userDto2.setId(UUID.randomUUID());
        userDto2.setFirstName("test2");
        userDto2.setLastName("test2");
        userServiceImpl.save(userDto2);
        List<UserDto>users = Arrays.asList(userDto, userDto2);
        Mockito.when(userServiceImpl.findAll()).thenReturn(users);
        Assertions.assertEquals(2, userServiceImpl.findAll().size());
        System.out.println(users);
    }

}
