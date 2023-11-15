package com.metrica.vibely.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.State;
import com.metrica.vibely.data.model.enumerator.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * 
 * @since 2023-11-13
 * @version 1.0
 */
@Entity
public class User {
    
    // <<-FIELDS->>

    // Basic
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID userId;
    
    private String username;
    private String password;
    private String nickname;
    private String email;
    
    @Column(name = "api_key")
    private String apiKey;
    
    @Enumerated(value = EnumType.STRING)
    private PrivacyType privacy;

    @Enumerated(value = EnumType.STRING)
    private Status status;
    
    @Enumerated(value = EnumType.STRING)
    private State state;

	private Integer logins;
	
	@Column(name = "blocked_date")
    private LocalDate blockedDate;
	
	@Column(name = "last_connection_date")
    private LocalDateTime lastConnDate;

    // Relations
    @OneToMany
    private Set<User> followers;

    @OneToMany
    private Set<User> following;

    @OneToMany(mappedBy = "postId")
    private Set<Post> posts;

    @OneToMany(mappedBy = "chatId")
    private Set<Chat> chats;

    // <<-CONSTRUCTORS->>
    public User() {
    }

    public User(
    		UUID userId,
    		String username,
    		String password, 
    		String nickname, 
    		String email, 
    		String apiKey, 
    		Set<User> followers,
            Set<User> following, 
            Set<Chat> chats, 
            Set<Post> posts, 
            PrivacyType privacy, 
            Status status, 
            State state,
            Integer logins, 
            LocalDate blockedDate, 
            LocalDateTime lastConnDate) {
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
        this.setNickname(nickname);
        this.setEmail(email);
        this.setApiKey(apiKey);
        this.setFollowers(followers);
        this.setFollowing(following);
        this.setPosts(posts);
        this.setChats(chats);
        this.setPrivacy(privacy);
        this.setStatus(status);
        this.setState(state);
        this.setLogins(logins);
        this.setBlockedDate(blockedDate);
        this.setLastConnDate(lastConnDate);
    }

    // <<-GETTERS & SETTERS->>
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
    	if(userId == null) { this.userId = UUID.randomUUID(); }
        this.userId = userId;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public PrivacyType getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyType privacy) {
    	if(privacy == null) { this.privacy = PrivacyType.PUBLIC; }
        this.privacy = privacy;
    }

    public Status getStatus() {
        return status;
    }
    
    public State getState() {
		return state;
	}

	public void setState(State state) {
		if(state == null) { this.state = State.ENABLED; }
		this.state = state;
	}

    public void setStatus(Status status) {
    	if(status == null) { this.status = Status.OFFLINE; }
        this.status = status;
    }

    public Integer getLogins() {
        return logins;
    }

    public void setLogins(Integer logins) {
    	if(logins == null) { this.logins = 0; }
        this.logins = logins;
    }

    public LocalDate getBlockedDate() {
        return blockedDate;
    }

    public void setBlockedDate(LocalDate blockedDate) {
        this.blockedDate = blockedDate;
    }

	public LocalDateTime getLastConnDate() {
		return lastConnDate;
	}

	public void setLastConnDate(LocalDateTime lastConnDate) {
		this.lastConnDate = lastConnDate;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
