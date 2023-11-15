package com.metrica.vibely.data.model.dto;

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
	
	public LocalDate getLastConnectionDate() {
		return null;
	}
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public PrivacyType getPrivacyType() {
		return privacyType;
	}

	public void setPrivacyType(PrivacyType privacyType) {
		this.privacyType = privacyType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Integer getLogins() {
		return logins;
	}

	public void setLogins(Integer logins) {
		this.logins = logins;
	}

	public LocalDate getBlockedDate() {
		return blockedDate;
	}

	public void setBlockedDate(LocalDate blockedDate) {
		this.blockedDate = blockedDate;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public Set<User> getFollowing() {
		return following;
	}

	public void setFollowing(Set<User> following) {
		this.following = following;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Chat> getChats() {
		return chats;
	}

	public void setChats(Set<Chat> chats) {
		this.chats = chats;
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

}
