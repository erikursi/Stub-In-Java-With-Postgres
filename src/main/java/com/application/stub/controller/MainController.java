package com.application.stub.controller;

import com.application.stub.entity.Capital;
import com.application.stub.entity.LoginRequest;
import com.application.stub.entity.LoginResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MainController {
    @Autowired
    private ObjectMapper objectMapper;
    @GetMapping("/api/main")
    public String mainListener() {
        return "Hello World!";
    }
    @GetMapping("/api/get")
    public String getCapital() {
        Capital capital = new Capital("Amsterdam", "Netherlands", 921468);
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(capital);
        } catch (JsonProcessingException e) {
            System.out.print("Error with capital");
        }
        return jsonData;
    }
    @PostMapping("/api/post")
    public String postCapital(@RequestBody LoginRequest loginRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LoginResponse loginResponse = new LoginResponse(loginRequest.getLogin(), loginRequest.getPassword(), currentDateTime);
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(loginResponse);
        } catch (JsonProcessingException e) {
            System.out.print("Error with capital");
        }
        return jsonData;
    }
}
