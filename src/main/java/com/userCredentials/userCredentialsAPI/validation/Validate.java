package com.userCredentials.userCredentialsAPI.validation;

import com.userCredentials.userCredentialsAPI.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

public class Validate {
    public static User validatedUser(Map<String, String> requestBody) {
        Map<String, String> REQUIRED_FIELDS = new HashMap<>();
        REQUIRED_FIELDS.put("login", "login");
        REQUIRED_FIELDS.put("password", "password");
        REQUIRED_FIELDS.put("email", "email");
        final int MAX_FIELDS_LENGTH = 64;
        final int MIN_PASSWORD_LENGTH = 8;
        String fieldsByString = REQUIRED_FIELDS.values().toString();
        ArrayList<String> messages = new ArrayList<>();

        if (requestBody.size() != REQUIRED_FIELDS.size()) {
            messages.add(String.format("Должны быть только поля %s", fieldsByString));
        }

        if (
            !requestBody.containsKey(REQUIRED_FIELDS.get("login"))
                || !requestBody.containsKey(REQUIRED_FIELDS.get("password"))
        ) {
            messages.add(String.format("Должны быть обязательно поля %s", fieldsByString));
        }

        for (Map.Entry<String, String> entry : requestBody.entrySet()) {
            if (REQUIRED_FIELDS.containsValue(entry.getKey())) {
                if (entry.getValue().isBlank()) {
                    messages.add(String.format("Поле '%s' не может быть пустым", entry.getKey()));
                }
                if (entry.getValue().length() > MAX_FIELDS_LENGTH) {
                    messages.add(String.format(
                            "Поле '%s' не может быть длиннее %d символов",
                            entry.getKey(),
                            MAX_FIELDS_LENGTH
                    ));
                }
                if (
                        entry.getKey().equals(REQUIRED_FIELDS.get("password"))
                                && entry.getValue().length() < MIN_PASSWORD_LENGTH
                ) {
                    messages.add(String.format(
                            "Поле '%s' не может быть короче %d символов",
                            entry.getKey(),
                            MIN_PASSWORD_LENGTH
                    ));
                }
            }
        }
        if (!messages.isEmpty()) {
            String fullMessage = String.join("; ", messages);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, fullMessage);
        }
        return new User(
                requestBody.get("login"),
                requestBody.get("password"),
                requestBody.get("email"),
                new Date()
        );
    }
}
