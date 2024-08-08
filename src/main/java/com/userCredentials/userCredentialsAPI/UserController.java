package com.userCredentials.userCredentialsAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    final int DELAY_FROM_SECONDS = 1;
    final int DELAY_TO_SECONDS = 2;

    @GetMapping("/user")
    public ResponseString getStringHello(
            @RequestParam(value = "name", defaultValue = "World") String name
    ) throws InterruptedException {
        ResponseString response;
        response = new ResponseString(String.format("Hello %s!", name));
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return response;
    }

    @PostMapping("/user")
    public User postUser(
            @RequestParam(value = "login", defaultValue = "login") String login,
            @RequestParam(value = "password", defaultValue = "password") String password
    ) throws InterruptedException {
        Date date = new Date();
        User user;
        user = new User(login, password, date);
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return user;
    }
}
