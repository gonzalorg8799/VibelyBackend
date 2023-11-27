package com.metrica.vibely.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

import jakarta.persistence.Entity;

/**
 * <h1>Administrator Entity</h1>
 * 
 * @since 2023-11-13
 * @version 1.0
 * @author Adrian, Alex
 */
@Entity
public class Admin extends User {

    // <<-CONSTRUCTORS->>
    public Admin() {
        super();
    }
    
    public Admin(
            UUID adminId,
            String username,
            String password,
            String nickname,
            String email,
            String apikey,
            UserState state,
            PrivacyType privacy,
            Integer logins,
            UserStatus status,
            LocalDateTime lastConnDate,
            LocalDate blockedDate,
            Set<User> followers,
            Set<User> following,
            Set<Post> posts,
            Set<Chat> chats) {
    	super();
    }
    
}