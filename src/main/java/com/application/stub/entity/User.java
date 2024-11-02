package com.application.stub.entity;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class User {
    @NotNull(message = "Login cannot be null")
    @NotEmpty(message = "Login cannot be empty")
private String login;
    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
private String password;
    private Date date;
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    public User (String login, String password, String email, Date date) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
    }
    @JsonCreator
    public User (String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = new Date(System.currentTimeMillis());
    }

    public @NotNull(message = "Login cannot be null") @NotEmpty(message = "Login cannot be empty") String getLogin() {
        return login;
    }

    public void setLogin(@NotNull(message = "Login cannot be null") @NotEmpty(message = "Login cannot be empty") String login) {
        this.login = login;
    }

    public @NotNull(message = "Password cannot be null") @NotEmpty(message = "Password cannot be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password cannot be null") @NotEmpty(message = "Password cannot be empty") String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public @NotNull(message = "Email cannot be null") @NotEmpty(message = "Email cannot be empty") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", email='" + email + '\'' +
                '}';
    }
    public String toJson() {
        return "{" +
                "\"login\":" + "\"" + login + "\"" +
                ",\"password\":" + "\"" + password + "\"" +
                ",\"email\":" + "\"" + email + "\"" +
                ",\"date\":" + "\"" + date + "\"" +
                "}";
    }
}
