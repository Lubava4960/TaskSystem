package com.example.dao.user;

import com.example.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

    private final String SELECT_USER = "SELECT* FROM public.user";

    public List<UserDto> getAllUsers() {
        return jdbcTemplate.query(SELECT_USER, new UserRowMapper());
    }

    private static class UserRowMapper implements RowMapper<UserDto> {
        @Override
        public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDto user = new UserDto();

            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setId(java.util.UUID.fromString((rs.getString("id"))));


            return user;
        }
    }
    private static final String DELETE_USER_QUERY = """
            DELETE FROM public."user"
            WHERE id = ?
            """;

    public void deleteUser(UUID id) {
        int rowsAffected = jdbcTemplate.update(DELETE_USER_QUERY, id);

        if (rowsAffected == 0) {
            log.info("Пользователь с ID {} не найден.", id);
        } else {
            log.info("Пользователь с ID {} успешно удален.", id);
        }

    }
}
