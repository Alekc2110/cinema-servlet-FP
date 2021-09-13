package com.my.cinema.booking.model.entity;

import com.my.cinema.booking.model.enums.Role;

public class User extends Entity {

    private String name;
    private String password;
    private String email;
    private Role role;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public static class Builder {

        private String name;
        private String password;
        private String email;
        private Role role;


        public Builder setName(String name) {
            this.name = name;

            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;

            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;

            return this;
        }


        public Builder setRole(Role role) {
            this.role = role;

            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.password = this.password;
            user.role = this.role;
            user.email = this.email;
            return user;
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
