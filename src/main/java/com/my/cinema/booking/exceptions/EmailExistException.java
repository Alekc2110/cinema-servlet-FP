package com.my.cinema.booking.exceptions;

public class EmailExistException extends RuntimeException {
    public EmailExistException() {
    }

    public EmailExistException(String message) {
        super(message);
    }
}
