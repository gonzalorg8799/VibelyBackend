package com.metrica.vibely.data.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

/**
 * <h1>Administrator DTO</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Adri
 */
public class AdminDTO extends UserDTO{

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
            Set<UUID> chats) {
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
                chats);
    }
}
