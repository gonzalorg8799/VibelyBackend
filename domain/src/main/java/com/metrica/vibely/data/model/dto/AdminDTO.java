package com.metrica.vibely.data.model.dto;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * <h1>Administrator DTO</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Adri
 */
public class AdminDTO extends UserDTO {

    // <<-CONSTRUCTORS->>
    public AdminDTO() {
        super();
    }

    public AdminDTO(
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
            Set<UUID> followers,
            Set<UUID> following,
            Set<UUID> posts,
            Set<UUID> chats,
            Set<UUID> likes,
            Set<UUID> saves) {
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