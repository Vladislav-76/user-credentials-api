package com.userCredentials.userCredentialsAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    final int DELAY_FROM_SECONDS = 1;
    final int DELAY_TO_SECONDS = 2;

    @GetMapping("/user")
    public ResponseEntity<User> user() throws InterruptedException {
        String login = "fakeLogin";
        String password = "fakePassword";
        User user = new User(login, password);
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> user(
            @RequestBody User user
    ) throws InterruptedException {
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return ResponseEntity.ok(user);
    }
}
