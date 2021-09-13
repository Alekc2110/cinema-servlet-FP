package com.my.cinema.booking.dao.impl;

import com.my.cinema.booking.dao.interfaces.UserDao;
import com.my.cinema.booking.dao.mapper.Mapper;
import com.my.cinema.booking.dao.mapper.UserMapper;
import com.my.cinema.booking.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.my.cinema.booking.dao.constants.Queries.*;


public class JDBCUserDao implements UserDao {
    private static final Logger LOG = Logger.getLogger(JDBCUserDao.class);
    private Connection connection;
    private Mapper<User> userMapper;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> getById(Long id) {
        userMapper = new UserMapper();
        User entity = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID)) {
            ps.setLong(1, id);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + GET_USER_BY_ID);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                entity = userMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcUserDao", e);
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean checkUser(String email, String password) {
        try (PreparedStatement ps = connection.prepareStatement(GET_BY_EMAIL_PASSWORD)) {
            ps.setString(1, email);
            ps.setString(2, password);
            final ResultSet rs = ps.executeQuery();

            LOG.debug("Executed query" + GET_BY_EMAIL_PASSWORD);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                return true;
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcUserDao", e);
        }
        return false;
    }

    @Override
    public Optional<User> getUser(String email, String password) {
        userMapper = new UserMapper();
        User result = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_BY_EMAIL_PASSWORD)) {
            ps.setString(1, email);
            ps.setString(2, password);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + GET_BY_EMAIL_PASSWORD);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                result = userMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcUserDao", e);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public boolean saveUserRole(Long userId, Long roleId) {
        try (PreparedStatement ps = connection.prepareStatement(SAVE_USER_ROLE)) {
            ps.setLong(1, userId);
            ps.setLong(2, roleId);
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in saveUserRole UserDao: " + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailExists(String email) {
        return false;
    }

    @Override
    public Optional<Long> save(User user) {
        ResultSet generatedKey = null;
        try (PreparedStatement ps = connection.prepareStatement(SAVE_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            if (ps.executeUpdate() != 1) {
                return Optional.empty();
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                Long id = generatedKey.getLong(1);
                return Optional.of(id);
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveUser' UserDao: " + e);
            return Optional.empty();
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.info("SQLException when closing ResultSet in 'saveUser': " + e);
            }
        }
        return Optional.empty();
    }

    @Override
    public Long getRoleId(String roleName) {
        long roleId = 0;
        try (PreparedStatement ps = connection.prepareStatement(GET_ROLE_ID)) {
            ps.setString(1, roleName);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_ROLE_ID);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                roleId = rs.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("SQLException in 'getRoleId' UserDao", e);
        }
        return roleId;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
