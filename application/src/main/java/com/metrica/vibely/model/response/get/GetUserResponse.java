package com.metrica.vibely.model.response.get;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;

import java.util.Set;
import java.util.UUID;

/**
 * <h1>Basic Info Response</h1>
 * 
 * @since 2023-11-27
 * @version 1.0
 * @author Alex
 */
public class GetUserResponse {
    
    // <<-FIELDS->>
    private UUID userId;
    private String username;
    private String nickname;
    private String email;
    private UserState state;
    private PrivacyType privacy;
    private Set<UUID> followers;
    private Set<UUID> following;
    private Set<UUID> posts;
    
    // <<-CONSTRUCTOR->>
    public GetUserResponse() {
    }
    
    // <<-METHOD->>
    public GetUserResponse generateResponse(UserDTO dto) {
        this.setUserId   (dto.getUserId());
        this.setUsername (dto.getUsername());
        this.setNickname (dto.getNickname());
        this.setEmail    (dto.getEmail());
        this.setState    (dto.getState());
        this.setPrivacy  (dto.getPrivacy());
        this.setFollowers(dto.getFollowers());
        this.setFollowing(dto.getFollowing());
        this.setPosts    (dto.getPosts());
        
        return this;
    }
    
    // <<-GETTERS & SETTERS->>
    public UUID getUserId() {
        return userId;
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
        this.state = state;
    }

    public PrivacyType getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyType privacy) {
        this.privacy = privacy;
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
    
}