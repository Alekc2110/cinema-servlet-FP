package com.my.cinema.booking.service;

import com.my.cinema.booking.dao.DaoFactory;
import com.my.cinema.booking.dao.interfaces.UserDao;
import com.my.cinema.booking.exceptions.EmailExistException;
import com.my.cinema.booking.exceptions.EntityNotFoundException;
import com.my.cinema.booking.exceptions.EntitySaveDaoException;
import com.my.cinema.booking.model.entity.User;
import com.my.cinema.booking.utils.PasswordEncoder;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);

    private DaoFactory factory = DaoFactory.getInstance();
    private PasswordEncoder passwordEncoder;


    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findUserById(Long id) {
        try (UserDao dao = factory.createUserDao()) {
            LOG.debug("get user by id");
            return dao.getById(id);
        }
    }

    public boolean checkUserExists(String email, String password) {
        try (UserDao dao = factory.createUserDao()) {
            LOG.debug("check if user exists UserDao");
            return dao.checkUser(email, passwordEncoder.encode(password));
        }
    }

    public boolean saveUser(User user) {
        try (UserDao dao = factory.createUserDao()) {
            LOG.debug("created UserDao");
            boolean isTakenEmail = dao.isEmailExists(user.getEmail());
            if (isTakenEmail) {
                LOG.debug("e-mail is already taken, exception occurred");
                throw new EmailExistException("This email is already registered in DB");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Optional<Long> userIdOpt = dao.save(user);
            Long userId = userIdOpt.orElseThrow(EntitySaveDaoException::new);
            Long roleId = dao.getRoleId(user.getRole().getName());
            return dao.saveUserRole(userId, roleId);
        }
    }

    public User getUser(String email, String password) {
        try (UserDao dao = factory.createUserDao()) {
            LOG.debug("getUser UserDao");
            Optional<User> userOptional = dao.getUser(email, passwordEncoder.encode(password));
            return userOptional.orElseThrow(EntityNotFoundException::new);
        }
    }
}
