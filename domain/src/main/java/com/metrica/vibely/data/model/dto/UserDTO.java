package com.metrica.vibely.data.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.util.DeepCopyGenerator;

/**
 * <h1>User DTO</h1>
 * 
 * @since 2023-11-15
 * @version 1.0 
 * @author Raul
 *
 */
public class UserDTO {
	
	// <<-FIELDS->>
    
    // Basics
	private UUID 		  userId;
	private String 		  nickname;
	private String 		  username;
	private String 		  password;
	private String 		  email;
	private String		  apiKey;
	private PrivacyType   privacy;
	private UserStatus    status;
	private UserState  	  state;
	private Integer 	  logins;
	private LocalDate 	  blockedDate;
	private LocalDateTime lastConnDate;
	
	// Relations
	private Set<UUID> 	  followers;
	private Set<UUID>     following;
	private Set<UUID> 	  posts;
	private Set<UUID> 	  chats;
	
    // <<-CONSTRUCTORS->>
	public UserDTO() {
        this.setUserId   (null);
        this.setFollowers(null);
        this.setFollowing(null);
        this.setPosts    (null);
        this.setChats    (null);
	}	
	
	public UserDTO(
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
            Set<UUID> followers,
            Set<UUID> following,
            Set<UUID> posts,
            Set<UUID> chats) {
        this.setUserId      (userId);
        this.setUsername    (username);
        this.setPassword    (password);
        this.setNickname    (nickname);
        this.setEmail       (email);
        this.setState       (state);
        this.setPrivacy     (privacy);
        this.setLogins      (logins);
        this.setStatus      (status);
        this.setLastConnDate(lastConnDate);
        this.setBlockedDate (blockedDate);
        this.setFollowers   (followers);
        this.setFollowing   (following);
        this.setPosts       (posts);
        this.setChats       (chats);
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
        if (this.getClass() != obj.getClass())
            return false;
        UserDTO other = (UserDTO) obj;
        return Objects.equals(this.userId, other.userId);
    }
    
    // <<-GETTERS & SETTERS->>
    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(final UUID userId) {
    	if (userId == null) {
    	    this.userId = UUID.randomUUID();
    	} else {
            this.userId = userId;
    	}
    	// Another way
    	// this.userId = (userId == null) ? UUID.randomUUID() : userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public UserState getState() {
        return this.state;
    }

    public void setState(final UserState state) {
        if (state == null) {
            this.state = UserState.ENABLED;
        } else {
            this.state = state;
        }
    }

    public PrivacyType getPrivacy() {
        return this.privacy;
    }

    public void setPrivacy(final PrivacyType privacy) {
        if (privacy == null) {
            this.privacy = PrivacyType.PUBLIC;
        } else {
            this.privacy = privacy;
        }
    }

    public Integer getLogins() {
        return this.logins;
    }

    public void setLogins(final Integer logins) {
        if (logins == null) {
            this.logins = 0;
        } else {
            this.logins = logins;
        }
    }

    public UserStatus getStatus() {
        return this.status;
    }

    public void setStatus(final UserStatus status) {
        if (status == null) {
            this.status = UserStatus.OFFLINE;
        } else {
            this.status = status;   
        }
    }

    public LocalDateTime getLastConnDate() {
        return this.lastConnDate;
    }

    public void setLastConnDate(final LocalDateTime lastConnDate) {
        if (lastConnDate == null) {
            this.lastConnDate = LocalDateTime.now();
        } else {
            this.lastConnDate = lastConnDate;   
        }
    }

    public LocalDate getBlockedDate() {
        return this.blockedDate;
    }

    public void setBlockedDate(final LocalDate blockedDate) {
        this.blockedDate = blockedDate;
    }

    public Set<UUID> getFollowers() {
        return this.followers;
    }

    public void setFollowers(final Set<UUID> followers) {
        this.followers = new HashSet<>();
        if (followers != null) {
            this.followers.addAll(followers);
        }
    }

    public Set<UUID> getFollowing() {
        return this.following;
    }

    public void setFollowing(final Set<UUID> following) {
        this.following = new HashSet<>();
        if (following != null) {
            this.following.addAll(following);
        }
    }

    public Set<UUID> getPosts() {
        return this.posts;
    }

    public void setPosts(final Set<UUID> posts) {
        this.posts = new HashSet<>();
        if (posts != null) {
            this.posts.addAll(posts);
        }
    }

    public Set<UUID> getChats() {
        return this.chats;
    }

    public void setChats(final Set<UUID> chats) {
        this.chats = new HashSet<>();
        if (chats != null) {
            this.chats.addAll(chats);
        }
    }
    

}
