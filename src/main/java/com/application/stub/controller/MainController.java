package com.application.stub.controller;
import com.application.stub.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {
    private static final User STATIC_USER = new User("Alisa", "XXX0088");
    @Autowired
    private ObjectMapper objectMapper;
    private void addRandomDelay() {
        try {
            int delay = (int) (1000 + Math.random() * 1000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser() {
        addRandomDelay();
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(STATIC_USER));
        } catch (JsonProcessingException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Failed to process capital data\"}");
        }

    }

    @PostMapping("/postUser")
    public ResponseEntity<?> postUser(@RequestBody @Valid User user) {
        addRandomDelay();
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Failed to process login response\"}");

        }
    }
    @PostMapping("/postUser2")
    public ResponseEntity<?> postUser2(@RequestBody Map<String, Object> userMap) {
        if (!userMap.containsKey("login") || !userMap.containsKey("password")) {
            return ResponseEntity.badRequest().body("{\"error\": \"Login and password are required\"}");
        }
        if (userMap.size() > 2) {
            return ResponseEntity.badRequest().body("{\"error\": \"Extra field detected\"}");
        }
        String login = (String) userMap.get("login");
        String password = (String) userMap.get("password");
        if (login == null || password == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Login and password cannot be null\"}");
        }
        if (login.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Login and password cannot be empty\"}");
        }
        addRandomDelay();
        User user = new User(login, password);
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Failed to process user data response\"}");
        }

    }
}
