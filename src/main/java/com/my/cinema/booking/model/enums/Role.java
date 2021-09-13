package com.my.cinema.booking.model.enums;

import com.my.cinema.booking.model.entity.User;

public enum Role {
    USER, ADMIN;

    public static Role getRole(User user) {
        return user.getRole();
    }

    public String getName() {
        return name().toLowerCase();
    }
}
