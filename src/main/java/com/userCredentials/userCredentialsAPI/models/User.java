package com.userCredentials.userCredentialsAPI.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class User {

    @NotBlank(message = "Поле 'login' обязательно")
    @Size(max = 64, message = "Поле 'login' должно быть до 64 символов")
    private final String login;

    @NotBlank(message = "Поле 'password' обязательно")
    @Size(min = 8, max = 64, message = "Поле 'password' должно быть от 8 до 64 символов")
    private final String password;

    @Size(max = 64, message = "Поле 'email' должно быть до 64 символов")
    private final String email;

    private final Date date;

    public User(String login, String password, String email, Date date) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate() {
        return date;
    }
}
