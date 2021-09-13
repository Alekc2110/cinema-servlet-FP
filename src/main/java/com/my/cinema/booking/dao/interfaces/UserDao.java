package com.my.cinema.booking.dao.interfaces;

import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;

import java.util.Optional;

public interface UserDao extends AutoCloseable {

    Optional<User> getById(Long id);

    boolean checkUser(String email, String password);

    Optional<User> getUser(String email, String password);

    boolean saveUserRole(Long userId, Long roleId);

    boolean isEmailExists(String email);

    Optional<Long> save(User user);

    Long getRoleId(String roleName);

    void close();
}
