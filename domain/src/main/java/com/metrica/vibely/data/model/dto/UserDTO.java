package com.metrica.vibely.data.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

/**
 * @since 2023-11-15
 * @version 1.0 
 * @author Raul
 *
 */
public class UserDTO {
	
	private UUID 		  userId;
	
	private String 		  nickname;
	private String 		  username;
	private String 		  password;
	private String 		  email;
	private String		  apiKey;
	
	private PrivacyType   privacy;
	private UserStatus 		  status;
	private UserState  		  state;
	
	private Integer 	  logins;
	private LocalDate 	  blockedDate;
	private LocalDateTime lastConnDate;
	
	private Set<UUID> 	  followers;
	private Set<UUID>     following;
	
	private Set<UUID> 	  posts;
	
	private Set<UUID> 	  chats;
	
	
	public UserDTO() {
	}
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public PrivacyType getPrivacy() {
		return privacy;
	}

	public void setPrivacy(PrivacyType privacy) {
		this.privacy = privacy;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
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

	public Set<UUID> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UUID> followers) {
		this.followers = followers;
	}

	public Set<UUID> getFollowing() {
		return following;
	}

	public void setFollowing(Set<UUID> following) {
		this.following = following;
	}

	public Set<UUID> getPosts() {
		return posts;
	}

	public void setPosts(Set<UUID> posts) {
		this.posts = posts;
	}

	public Set<UUID> getChats() {
		return chats;
	}

	public void setChats(Set<UUID> chats) {
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

	/**
	 * returns users' last connection date
	 * @return 
	 */
	public LocalDateTime getLastConnDate() {
		if(status.equals(UserStatus.OFFLINE)) { return lastConnDate; }
		else { return LocalDateTime.now(); }
	}
	
	public void setLastConnection(LocalDateTime lastConnDate) {
		this.lastConnDate = lastConnDate;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
