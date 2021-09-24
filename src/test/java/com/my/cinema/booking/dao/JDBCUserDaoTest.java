package com.my.cinema.booking.dao;


import com.my.cinema.booking.dao.impl.JDBCTicketDao;
import com.my.cinema.booking.dao.impl.JDBCUserDao;
import com.my.cinema.booking.dao.interfaces.TicketDao;
import com.my.cinema.booking.dao.interfaces.UserDao;
import com.my.cinema.booking.model.entity.Movie;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.my.cinema.booking.dao.constants.Queries.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JDBCUserDaoTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement prepareStatement;
    @Mock
    private ResultSet rs;

    private UserDao userDao;

    @BeforeEach
    void setUp(){
        userDao = new JDBCUserDao(connection);
    }

    @Test
    @DisplayName("should return optional of user by id")
    void getByIdTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_USER_BY_ID))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);

        Assertions.assertEquals(Optional.empty(),userDao.getById(1L));
    }

    @Test
    @DisplayName("should return true if user exists")
    void checkUserPositiveTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_BY_EMAIL_PASSWORD))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        Assertions.assertTrue(userDao.checkUser("some@gmail.com", "1111"));
    }

    @Test
    @DisplayName("should return false if user does not exist")
    void checkUserNegativeTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_BY_EMAIL_PASSWORD))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        Assertions.assertFalse(userDao.checkUser("some@gmail.com", "1111"));
    }

    @Test
    @DisplayName("should return user")
    void getUserPositiveTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_BY_EMAIL_PASSWORD))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);

        Assertions.assertEquals(Optional.empty(),userDao.getUser("some@gmail.com", "1111"));
    }

    @Test
    @DisplayName("should return optional empty if user does not exist")
    void getUserNegativeTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_BY_EMAIL_PASSWORD))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        Assertions.assertEquals(Optional.empty(),userDao.getUser("some@gmail.com", "1111"));
    }

    @Test
    @DisplayName("should save user role")
    void saveUserRoleTest() throws SQLException {
        when(connection.prepareStatement(eq(SAVE_USER_ROLE))).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);

        Assertions.assertTrue(userDao.saveUserRole(1L,1L));
    }

    @Test
    @DisplayName("should return true if email exists in db")
    void isEmailExistsPositiveTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_USER_BY_EMAIL))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        Assertions.assertTrue(userDao.isEmailExists("some@gmail.com"));
    }

    @Test
    @DisplayName("should return false if email does not exists in db")
    void isEmailExistsNegativeTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_USER_BY_EMAIL))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        Assertions.assertFalse(userDao.isEmailExists("some@gmail.com"));
    }

    @Test
    @DisplayName("should save new user")
    void saveTest() throws SQLException {
        User user = new User.Builder().setRole(Role.USER).setName("name").setEmail("some@gmail.com").setPassword("1111").build();

        when(connection.prepareStatement(eq(SAVE_USER), anyInt())).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);
        when(prepareStatement.getGeneratedKeys()).thenReturn(rs);

        Assertions.assertEquals(Optional.empty(),userDao.save(user));
    }

    @Test
    @DisplayName("should return optional empty if throws exception")
    void saveExcTest() throws SQLException {
        User user = new User.Builder().setRole(Role.USER).setName("name").setEmail("some@gmail.com").setPassword("1111").build();

        when(connection.prepareStatement(eq(SAVE_USER), anyInt())).thenReturn(prepareStatement);

        when(prepareStatement.executeUpdate()).thenReturn(1);
        when(prepareStatement.getGeneratedKeys()).thenThrow(SQLException.class);

        Assertions.assertEquals(Optional.empty(),userDao.save(user));
    }

    @Test
    @DisplayName("should return return role id")
    void getRoleIdTest() throws SQLException {
        when(connection.prepareStatement(eq(GET_ROLE_ID))).thenReturn(prepareStatement);

        when(prepareStatement.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        Assertions.assertEquals(anyLong(),userDao.getRoleId(String.valueOf(Role.USER)));
    }

}
