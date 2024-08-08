package com.userCredentials.userCredentialsAPI;

import org.springframework.web.bind.annotation.*;

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
    public User postUser(@RequestBody User user) throws InterruptedException {
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return user;
    }
}
