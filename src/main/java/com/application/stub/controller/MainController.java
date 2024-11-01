package com.application.stub.controller;
import com.application.stub.entity.DBService;
import com.application.stub.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private DBService dbService;
    private void addRandomDelay() {
        try {
            int delay = (int) (1000 + Math.random() * 1000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    @GetMapping("/getUser/{login}")
    public ResponseEntity<?> getUserbyLogin(@PathVariable String login) {
        addRandomDelay();
        try {
            User user = dbService.getUserByLogin(login);
            return ResponseEntity.ok(user);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Database error\"}");
        }

    }

    @PostMapping("/postUser")
    public ResponseEntity<?> postUser(@RequestBody @Valid User user) {
        addRandomDelay();
        try {
            dbService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Database error\"}");
        }
    }

    @PostMapping("/postUser2")
    public ResponseEntity<?> postUser2(@RequestBody Map<String, Object> userMap) {
        if (!userMap.containsKey("login") || !userMap.containsKey("password")  || !userMap.containsKey("email") || userMap.size() > 3) {
            return ResponseEntity.badRequest().body("{\"error\": \"Bad json\"}");
        }
        String login = (String) userMap.get("login");
        String password = (String) userMap.get("password");
        String email = (String) userMap.get("email");
        if (login == null || password == null || email == null || login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Bad json\"}");
        }
        addRandomDelay();
        User user = new User(login, password, email);
        try {
            dbService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Database error\"}");
        }
    }
}
