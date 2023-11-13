package com.metrica.vibely.model;

import java.util.List;

import com.metrica.vibely.model.enumerator.PrivacyType;
import com.metrica.vibely.model.enumerator.Status;

public class Admin extends User{
	   // <<-CONSTRUCTORS->>
    public Admin() {
        
    }

    public Admin(
            java.util.UUID userId,
            String username,
            String password,
            String nickname,
            String email,
            List<User> followers,
            List<User> following,
            List<Chat> chats,
            List<Post> posts,
            PrivacyType privacyType,
            Status status,
            Integer logins,
            java.time.LocalDate blockedDate){
    	super(userId,
    		  username,
    		  password,
    		  nickname,
    		  email,
    		  followers,
    		  following,
    		  chats,
    		  posts,
    		  privacyType,
    		  status,
    		  logins,
    		  blockedDate);
        
    }
    
}
