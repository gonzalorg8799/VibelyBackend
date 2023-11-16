package com.metrica.vibely.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * <h1>User Entity</h1>
 * 
 * @since 2023-11-13
 * @version 1.0
 * @author Adrian, Alex
 */
@Entity
public class User {
    
    // <<-FIELDS->>

    // Basic
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private UserState state;
    @Enumerated(value = EnumType.STRING)
    private PrivacyType privacy;
    private Integer logins;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;
	@Column(name = "last_connection_date")
    private LocalDateTime lastConnDate;
	@Column(name = "blocked_date")
	private LocalDate blockedDate;

    // Relations
    @OneToMany(mappedBy = "userId")
    private Set<User> followers;
    @OneToMany(mappedBy = "userId")
    private Set<User> following;
    @OneToMany(mappedBy = "postId")
    private Set<Post> posts;
    @OneToMany(mappedBy = "chatId")
    private Set<Chat> chats;

    // <<-CONSTRUCTORS->>
    public User() {
        this.setUserId(null);
        this.posts = new Post.TreePost();
    }

    public User(
            UUID userId,
            String username,
            String password,
            String nickname,
            String email,
            UserState state,
            PrivacyType privacy,
            Integer logins,
            UserStatus status,
            LocalDateTime lastConnDate,
            LocalDate blockedDate,
            Set<User> followers,
            Set<User> following,
            Set<Post> posts,
            Set<Chat> chats
     ) {
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
        this.setNickname(nickname);
        this.setEmail(email);
        this.setState(state);
        this.setPrivacy(privacy);
        this.setLogins(logins);
        this.setStatus(status);
        this.setLastConnDate(lastConnDate);
        this.setBlockedDate(blockedDate);
        this.setFollowers(followers);
        this.setFollowing(following);
        this.posts = new Post.TreePost();
        this.setPosts(posts);
        this.setChats(chats);
    }

    // <<-METHODS->>
    @Override
    public int hashCode() {
        return Objects.hash(this.userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(this.userId, other.userId);
    }
    
    // <<-GETTERS & SETTERS->>
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
    	if (userId == null) {
    	    this.userId = UUID.randomUUID();
    	} else {
            this.userId = userId;
    	}
    	// Another way
    	// this.userId = (userId == null) ? UUID.randomUUID() : userId;
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

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        if (state == null) {
            this.state = UserState.ENABLED;
        } else {
            this.state = state;
        }
    }

    public PrivacyType getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyType privacy) {
        if (privacy == null) {
            this.privacy = PrivacyType.PUBLIC;
        } else {
            this.privacy = privacy;
        }
    }

    public Integer getLogins() {
        return logins;
    }

    public void setLogins(Integer logins) {
        if (logins == null) {
            this.logins = 0;
        } else {
            this.logins = logins;
        }
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        if (status == null) {
            this.status = UserStatus.OFFLINE;
        } else {
            this.status = status;   
        }
    }

    public LocalDateTime getLastConnDate() {
        return lastConnDate;
    }

    public void setLastConnDate(LocalDateTime lastConnDate) {
        if (lastConnDate == null) {
            this.lastConnDate = LocalDateTime.now();
        } else {
            this.lastConnDate = lastConnDate;   
        }
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
    
}