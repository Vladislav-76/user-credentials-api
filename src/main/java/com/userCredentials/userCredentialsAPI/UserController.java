package com.userCredentials.userCredentialsAPI;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/user/fields_validate")
    public ResponseEntity<User> userFieldsValidate(
            @Valid @RequestBody User user
    ) throws InterruptedException {
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/map_validate")
    public ResponseEntity<User> userMapValidate(
            @RequestBody HashMap<String, String> requestBody
    ) throws InterruptedException {
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return ResponseEntity.ok(Validate.validatedUser(requestBody));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
