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
    private static final User STATIC_USER = new User("Alisa", "XXX0088", "email");
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
        if (!userMap.containsKey("login") || !userMap.containsKey("password")  || !userMap.containsKey("email")) {
            return ResponseEntity.badRequest().body("{\"error\": \"Login, password and email are required\"}");
        }
        if (userMap.size() > 3) {
            return ResponseEntity.badRequest().body("{\"error\": \"Extra field detected\"}");
        }
        String login = (String) userMap.get("login");
        String password = (String) userMap.get("password");
        String email = (String) userMap.get("email");
        if (login == null || password == null || email == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"User fields cannot be null\"}");
        }
        if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"User fields cannot be empty\"}");
        }
        addRandomDelay();
        User user = new User(login, password, email);
        return ResponseEntity.ok(user);
    }
}
