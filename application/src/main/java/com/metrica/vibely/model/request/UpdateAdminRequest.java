package com.metrica.vibely.model.request;

import com.metrica.vibely.data.model.dto.AdminDTO;

import jakarta.validation.constraints.Pattern;

/**
 * <h1>Update Admin Request</h1>
 * 
 * @since 2023-11-22
 * @version 1.0
 * @author Alex
 */
public class UpdateAdminRequest {

    // <<-FIELDS->>
    private String username;
    @Pattern(regexp = "")
    private String password;
    private String nickname;
    private String email;

    // <<-CONSTRUCTOR->>
    public UpdateAdminRequest() {
    }
    
    // <<-METHODS->>
    public AdminDTO toDTO() {
        AdminDTO adminDTO = new AdminDTO();
        
        adminDTO.setUsername(this.username);
        adminDTO.setPassword(this.password);
        adminDTO.setNickname(this.nickname);
        adminDTO.setEmail   (this.email);
        
        return adminDTO;
    }
    
    // <<-GETTERS & SETTERS->>
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
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