package com.metrica.vibely.data.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    
    // <<-FIELDS->>
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID postId;
	private String content;
	private Integer likes;
	private Integer saved;
	private List<Post> comments;
	private User user;

    // <<-CONSTRUCTORS->>
	public Post() {
	}
	
    public Post(
            UUID postId,
            String content,
            Integer likes,
            Integer saved,
            List<Post> comments,
            User user) {
        this.setPostId(postId);
        this.setContent(content);
        this.setLikes(likes);
        this.setSaved(saved);
        this.setComments(comments);
        this.setUser(user);
    }

    // <<-GETTERS & SETTERS->>
	public UUID getPostId() {
		return postId;
	}
	
    public void setPostId() {
    	this.setPostId(UUID.randomUUID());
    }
    
    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes == null ? 0 : likes;
    }

    public Integer getSaved() {
        return saved;
    }

    public void setSaved(Integer saved) {
        this.saved = saved == null ? 0 : saved;
    }

    public List<Post> getComments() {
        return comments;
    }

    public void setComments(List<Post> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
