package com.userCredentials.userCredentialsAPI;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    final int DELAY_FROM_SECONDS = 1;
    final int DELAY_TO_SECONDS = 2;

    @GetMapping("/user")
    public User getUser() throws InterruptedException {
        String login = "loginExample";
        String password = "passwordExample";
        User user = new User(login, password);
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return user;
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) throws InterruptedException {
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return user;
    }
}
