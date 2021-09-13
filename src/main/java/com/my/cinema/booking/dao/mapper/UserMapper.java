package com.my.cinema.booking.dao.mapper;

import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        User user = new User.Builder()
                .setName(resultSet.getString("name"))
                .setPassword(resultSet.getString("password"))
                .setRole(Role.valueOf(resultSet.getString("role_name")))
                .setEmail(resultSet.getString("email"))
                .build();
        user.setId(resultSet.getLong("id"));
        return user;
    }
}
