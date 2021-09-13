package com.my.cinema.booking.exceptions;

public class EntitySaveDaoException extends RuntimeException {
    public EntitySaveDaoException() {
    }

    public EntitySaveDaoException(String message) {
        super(message);
    }
}
