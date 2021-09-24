package com.my.cinema.booking.mapper;

import com.my.cinema.booking.dao.mapper.UserMapper;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    @Test
    void getEntityTest() throws SQLException {

        UserMapper userMapper = new UserMapper();
        ResultSet rs = mock(ResultSet.class);
        User expected = new User.Builder().
                setName("name").
                setEmail("test@mail.com").
                setRole(Role.USER).
                setPassword("1111").build();
        expected.setId(1L);


        lenient().when(rs.getLong("id")).thenReturn(1L);
        lenient().when(rs.getString("name")).thenReturn("name");
        lenient().when(rs.getString("password")).thenReturn("1111");
        lenient().when(rs.getString("role_name")).thenReturn(String.valueOf(Role.USER));
        lenient().when(rs.getString("email")).thenReturn("test@mail.com");

        User result = userMapper.getEntity(rs);

        Assertions.assertAll(() ->
        {
            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getRole(), result.getRole());
            assertEquals(expected.getName(), result.getName());
            assertEquals(expected.getEmail(), result.getEmail());
            assertEquals(expected.getPassword(), result.getPassword());
        });
    }
}

