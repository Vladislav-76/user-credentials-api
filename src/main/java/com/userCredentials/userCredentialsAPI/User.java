package com.userCredentials.userCredentialsAPI;

import java.util.Date;

public class User {
    private String login;
    private String password;
    private Date date;

    public User(String login, String password, Date date) {
        this.login = login;
        this.password = password;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
