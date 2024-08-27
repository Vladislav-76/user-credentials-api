package com.userCredentials.userCredentialsAPI.controller;

import com.userCredentials.userCredentialsAPI.validation.Validate;
import com.userCredentials.userCredentialsAPI.db.ServiceDB;
import jakarta.validation.Valid;
import com.userCredentials.userCredentialsAPI.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    final int DELAY_FROM_SECONDS = 1;
    final int DELAY_TO_SECONDS = 2;

    @GetMapping("/user")
    public ResponseEntity<User> user(@RequestParam("login") String login) throws InterruptedException, SQLException {
        User user = new ServiceDB().userSelect(login);
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/fields_validate")
    public ResponseEntity<User> userFieldsValidate(
            @Valid @RequestBody User user
    ) throws InterruptedException, SQLException {
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        ServiceDB.userInsert(user);
        return ResponseEntity.ok(new ServiceDB().userSelect(user.getLogin()));
    }

    @PostMapping("/user/map_validate")
    public ResponseEntity<User> userMapValidate(
            @RequestBody Map<String, String> requestBody
    ) throws InterruptedException, SQLException {
        Delay.randomDelay(DELAY_FROM_SECONDS, DELAY_TO_SECONDS);
        User user = Validate.validatedUser(requestBody);
        ServiceDB.userInsert(user);
        return ResponseEntity.ok(user);
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
