package com.metrica.vibely.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.util.Copyable;
import com.metrica.vibely.data.util.DeepCopyGenerator;

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
public class User implements Copyable<User> {

    // <<-FIELDS->>

    // Basic
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String apikey;
    @Enumerated(value = EnumType.ORDINAL)
    private UserState state;
    @Enumerated(value = EnumType.ORDINAL)
    private PrivacyType privacy;
    private Integer logins;
    @Enumerated(value = EnumType.ORDINAL)
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
    public User(UUID userId) {
    	this.setUserId(userId);
    }
    
    public User() {
        this.setUserId   (null);
        this.setFollowers(null);
        this.setFollowing(null);
        this.setPosts    (null);
        this.setChats    (null);
    }

    public User(
            UUID userId,
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
            Set<Chat> chats) {
        this.setUserId      (userId);
        this.setUsername    (username);
        this.setPassword    (password);
        this.setNickname    (nickname);
        this.setEmail       (email);
        this.setApikey      (apikey);
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
    public User deepCopy() {
        User copy = new User();
        
        copy.setUserId      (this.userId);
        copy.setUsername    (this.username);
        copy.setPassword    (this.password);
        copy.setNickname    (this.nickname);
        copy.setEmail       (this.email);
        copy.setState       (this.state);
        copy.setPrivacy     (this.privacy);
        copy.setLogins      (this.logins);
        copy.setStatus      (this.status);
        copy.setLastConnDate(this.lastConnDate);
        copy.setBlockedDate (this.blockedDate);
        copy.setFollowers   (this.followers);
        copy.setFollowing   (this.following);
        copy.setPosts       (this.posts);
        copy.setChats       (this.chats);
        
        return copy;
    }
    
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
        User other = (User) obj;
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

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
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

    public Set<User> getFollowers() {
        return DeepCopyGenerator.generateCopy(this.followers);
    }

    public void setFollowers(final Set<User> followers) {
        this.followers = new HashSet<>();
        if (followers != null) {
            this.followers.addAll(DeepCopyGenerator.generateCopy(followers));
        }
    }

    public Set<User> getFollowing() {
        return DeepCopyGenerator.generateCopy(this.following);
    }

    public void setFollowing(final Set<User> following) {
        this.following = new HashSet<>();
        if (following != null) {
            this.following.addAll(DeepCopyGenerator.generateCopy(following));
        }
    }

    public Set<Post> getPosts() {
        return DeepCopyGenerator.generateCopy(this.posts);
    }

    public void setPosts(final Set<Post> posts) {
        this.posts = new Post.TreePost();
        if (posts != null) {
            this.posts.addAll(DeepCopyGenerator.generateCopy(posts));
        }
    }

    public Set<Chat> getChats() {
        return DeepCopyGenerator.generateCopy(this.chats);
    }

    public void setChats(final Set<Chat> chats) {
        this.chats = new HashSet<>();
        if (chats != null) {
            this.chats.addAll(DeepCopyGenerator.generateCopy(chats));
        }
    }

    
}