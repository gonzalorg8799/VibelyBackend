package com.metrica.vibely.model.response;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.AdminDTO;

/**
 * <h1>Create Administrator Response Body</h1>
 * 
 * @since 2023-11-27
 * @version 1.0
 * @author Alex
 */
public class CreateAdminResponse {

    // <<-FIELDS->>
    private UUID userId;
    private String username;
    private String nickname;
    private String email;

    // <<-CONSTRUCTOR->>
    public CreateAdminResponse() {
    }

    // <<-METHODS->>
    public CreateAdminResponse generateResponse(AdminDTO adminDto) {
        this.setUserId  (adminDto.getUserId());
        this.setUsername(adminDto.getUsername());
        this.setNickname(adminDto.getNickname());
        this.setEmail   (adminDto.getEmail());
        return this;
    }

    // <<-GETTERS & SETTERS->>
    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(final UUID userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}