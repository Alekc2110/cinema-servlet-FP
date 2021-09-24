package com.my.cinema.booking.service;


import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.UserDao;
import com.my.cinema.booking.exceptions.EmailExistException;
import com.my.cinema.booking.exceptions.EntityNotFoundException;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.model.enums.Role;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    @Mock
    private DaoFactory factory;

    private UserService userService;

    @BeforeEach
    void setUp() {
        setMock(factory);
        userService = new UserService();
        when(factory.createUserDao()).thenReturn(userDao);
    }

    @AfterEach
    public void resetSingleton() throws Exception {
        Field instance = DaoFactory.class.getDeclaredField("daoFactory");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @Test
    @DisplayName("check if user is saved in DB")
    void checkUserExistsTest() {
        String password = "1111";
        String encPassword = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
        String email = "admin1@gmail.com";
        when(userDao.checkUser(email, encPassword)).thenReturn(true);
        boolean result = userService.checkUserExists(email, password);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("should return user by email and pass")
    void getUserTest() {
        String password = "1111";
        String encPassword = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
        String email = "admin1@gmail.com";
        User user = new User.Builder()
                .setName("test")
                .setEmail("admin1@gmail.com")
                .setPassword("1111")
                .build();
        when(userDao.getUser(email, encPassword)).thenReturn(Optional.of(user));

        User returnedUser = userService.getUser(email, password);

        Assertions.assertEquals("test", returnedUser.getName());
    }

    @Test
    @DisplayName("should throw exception if user does not exists")
    void getUserIfNullTest() {
        String password = "1111";
        String encPassword = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
        String email = "admin1@gmail.com";
        when(userDao.getUser(email, encPassword)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.getUser(email, password));
    }

    @Test
    @DisplayName("should save new user")
    void saveUserTest() {
        Long newId = 1L;
        Long roleId = 2L;
        User user = new User.Builder().
                setName("name").
                setEmail("test@mail.com").
                setRole(Role.USER).
                setPassword("1111").build();

        when(userDao.save(user)).thenReturn(Optional.of(newId));
        when(userDao.isEmailExists(user.getEmail())).thenReturn(false);

        when(userDao.getRoleId(user.getRole().getName())).thenReturn(2L);
        when(userDao.saveUserRole(newId,roleId)).thenReturn(true);

        boolean saved = userService.saveUser(user);
        Assertions.assertTrue(saved);
    }


    @Test
    @DisplayName("should throw exception if email already exists in DB")
    void saveUserThrowExTest() {
        User user = new User.Builder().
                setName("name").
                setEmail("test@mail.com").
                setRole(Role.USER).
                setPassword("1111").build();
        when(userDao.isEmailExists(user.getEmail())).thenReturn(true);

        Assertions.assertThrows(EmailExistException.class, ()-> userService.saveUser(user));
    }


    private void setMock(DaoFactory mock) {
        Field instance;
        try {
            instance = DaoFactory.class.getDeclaredField("daoFactory");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
