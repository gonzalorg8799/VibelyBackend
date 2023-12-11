package com.metrica.vibely.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * @since 2023-11-14
 * @version 1.0
 */
public class AdminAuthEmailRequest {

    // <<-FIELDS->>
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[@$!^%#*&])(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])[A-Za-z\\d@$!%^*#&]{12,}$")
    private String password;

    // <<-CONSTRUCTOR->>
    public AdminAuthEmailRequest() {
    }

    // <<-GETTERS & SETTERS->>
    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
