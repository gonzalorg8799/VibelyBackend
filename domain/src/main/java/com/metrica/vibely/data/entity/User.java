package com.metrica.vibely.data.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.PrivacyType;
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
    @Column(name = "userId", nullable = false)
    private UUID userId;
    private String username, password, nickname, email;

    @Enumerated(value = EnumType.STRING)
    private PrivacyType privacyType;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private Integer logins;
    private LocalDate blockedDate;

    // Relations
    @OneToMany
    private List<User> followers;

    @OneToMany
    private List<User> following;

    @OneToMany(mappedBy = "post_id")
    private List<Post> posts;

    @OneToMany(mappedBy = "chat_id")
    private List<Chat> chats;

    // <<-CONSTRUCTORS->>
    public User() {
    }

    public User(UUID userId, String username, String password, String nickname, String email, List<User> followers,
            List<User> following, List<Chat> chats, List<Post> posts, PrivacyType privacyType, Status status,
            Integer logins, LocalDate blockedDate) {
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
        this.setNickname(nickname);
        this.setEmail(email);
        this.setFollowers(followers);
        this.setFollowing(following);
        this.setPosts(posts);
        this.setChats(chats);
        this.setPrivacyType(privacyType);
        this.setStatus(status);
        this.setLogins(logins);
        this.setBlockedDate(blockedDate);
    }

    // <<-GETTERS & SETTERS->>
    public UUID getUserId() {
        return userId;
    }

    public void generateUserId() {
        this.setUserId(UUID.randomUUID());
    }

    public void setUserId(UUID userId) {
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

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
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
}
