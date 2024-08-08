package com.userCredentials.userCredentialsAPI;

import java.util.Date;

public class User {
    private final String login;
    private final String password;
    private final Date date;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.date = new Date();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate() {
        return date;
    }
}
