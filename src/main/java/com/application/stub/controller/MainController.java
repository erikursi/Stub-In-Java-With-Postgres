package com.application.stub.controller;

import com.application.stub.entity.Capital;
import com.application.stub.entity.LoginRequest;
import com.application.stub.entity.LoginResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private ObjectMapper objectMapper;
    private void addRandomDelay() {
        try {
            int delay = (int) (1000 + Math.random() * 1000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread was interrupted during sleep", e);
        }
    }
    @GetMapping("/api/get")
    public ResponseEntity<String> getCapital() {
        addRandomDelay();
        Capital capital = new Capital("Amsterdam", "Netherlands", 921468);
        String jsonData;
        try {
            jsonData = objectMapper.writeValueAsString(capital);
        } catch (JsonProcessingException e) {
            logger.error("Error processing Capital object to JSON", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Failed to process capital data\"}");
        }
        return ResponseEntity.ok(jsonData);
    }
    @PostMapping("/api/post")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        addRandomDelay();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        LoginResponse loginResponse = new LoginResponse(loginRequest.getLogin(), loginRequest.getPassword(), formattedDateTime);
        String jsonData;
        try {
            jsonData = objectMapper.writeValueAsString(loginResponse);
        } catch (JsonProcessingException e) {
            logger.error("Error processing LoginResponse object to JSON", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Failed to process login response\"}");

        }
        return ResponseEntity.ok(jsonData);
    }
}
