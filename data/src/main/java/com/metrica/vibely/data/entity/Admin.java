package com.metrica.vibely.data.entity;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * <h1>Administrator Entity</h1>
 * 
 * @since 2023-11-13
 * @version 1.0
 * @author Adrian, Alex
 */
@Entity
@Table
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
            Set<Chat> chats,
            Set<Post> likes,
            Set<Post> saves) {
        super(
                adminId,
                username,
                password,
                nickname,
                email,
                apikey,
                state,
                privacy,
                logins,
                status,
                lastConnDate,
                blockedDate,
                followers,
                following,
                posts,
                chats,
                likes,
                saves);
    }

}