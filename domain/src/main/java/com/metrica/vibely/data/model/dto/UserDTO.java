package com.metrica.vibely.data.model.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.State;
import com.metrica.vibely.data.model.enumerator.Status;

public class UserDTO {
	
	private UUID userId;
	
	private String nickname;
	private String username;
	private String password;
	private String email;
	
	private PrivacyType privacyType;
	private Status status;
	private State state;
	
	private Integer logins;
	private LocalDate blockedDate;
	
	private Set<User> followers;
	private Set<User> following;
	
	private Set<Post> posts;
	
	private Set<Chat> chats;
	
	public UserDTO() {
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getLastConnectionDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
