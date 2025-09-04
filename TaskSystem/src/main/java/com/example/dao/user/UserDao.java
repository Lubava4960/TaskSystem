package com.example.dao.user;


import com.example.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_USER = """
            
            INSERT INTO public."user" (id,
                                     first_name,
                                     last_name
                                 )
                                 VALUES (?::uuid, ?, ?)
            
            """;
    public void addUser(UserDto userDto) {
        if (userDto.getId() == null) {
            userDto.setId(UUID.randomUUID());
        }
        jdbcTemplate.update(INSERT_USER,
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName());

    }


}
