package com.metrica.vibely.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateUserRequest {

    // <<-FIELDS->>
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z_-\\d]*")
    private String password;

    @Pattern(regexp = "[a-zA-Z_-\\d]*")
    @NotBlank
    private String nickname;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotNull
    @NotBlank
    private String email;

    // <<-CONSTRUCTOR->>
    public CreateUserRequest() {
    }

    // <<-GETTERS & SETTERS->>
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
