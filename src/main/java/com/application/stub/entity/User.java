package com.application.stub.entity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @NotEmpty(message = "Login cannot be empty")
private String login;
    @NotEmpty(message = "Password cannot be empty")
private String password;
private final String timestamp;

    @JsonCreator
    public User(@JsonProperty("login") String login, @JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
