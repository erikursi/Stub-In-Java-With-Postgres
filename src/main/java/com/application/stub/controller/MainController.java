package com.application.stub.controller;
import com.application.stub.entity.User;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok(STATIC_USER);

    }

    @PostMapping("/postUser")
    public ResponseEntity<?> postUser(@RequestBody @Valid User user) {
        addRandomDelay();
        return ResponseEntity.ok(user);
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
        return ResponseEntity.ok(user);
    }
}
