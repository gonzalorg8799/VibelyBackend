package com.metrica.vibely.data.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ForeignKey;

/**
 * <h1>Post Entity </h1>
 * 
 * @since 2023-11-13
 * @version 1.0
 * @author Adrian, Alex
 */
@Entity
public class Post {
    
    // <<-FIELDS->>
    
    // Basics
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID postId;
	@Column(name = "post_date")
	private LocalDateTime postDate;
	private PostStatus status;
	private PostVisibility visibility;
	private String content;
	private Integer likes;
    @Column(name = "times_saved")
	private Integer timesSaved;
	
	// Relations
	@OneToOne(optional = false)
    @JoinColumn(
            name = "owner_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_post_user"))
    private User owner;
    @OneToMany()
	private Set<Post> comments;
    @OneToMany()
    private Set<User> likedBy;
    @OneToMany()
    private Set<User> savedBy;
    
    // <<-CONSTRUCTORS->>
	public Post() {
	    this.setPostId(null);
	    this.comments = new TreePost();
	}

    public Post(
            UUID postId,
            LocalDateTime postDate,
            PostStatus status,
            PostVisibility visibility,
            String content,
            Integer likes,
            Integer timesSaved,
            User owner,
            Set<Post> comments,
            Set<User> likedBy,
            Set<User> savedBy
    ) {
        this.setPostId(postId);
        this.setPostDate(postDate);
        this.setStatus(status);
        this.setVisibility(visibility);
        this.setContent(content);
        this.setLikes(likes);
        this.setTimesSaved(timesSaved);
        this.setOwner(owner);
        this.comments = new TreePost();
        this.setComments(comments);
        this.setLikedBy(likedBy);
        this.setSavedBy(savedBy);
    }

    // <<-METHODS->>
    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        return Objects.equals(this.postId, other.postId);
    }
    
    // <<-GETTERS & SETTERS->>
    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        if (postId == null) {
            this.postId = UUID.randomUUID();
        } else {
            this.postId = postId;
        }
        // Another way
        // this.postId = (postId == null) ? UUID.randomUUID() : postId;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        if (postDate == null) {
            this.postDate = LocalDateTime.now();
        } else {
            this.postDate = postDate;
        }
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        if (status == null) {
            this.status = PostStatus.ACTIVE;
        } else {
            this.status = status;
        }
    }

    public PostVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(PostVisibility visibility) {
        if (visibility == null) {
            this.visibility = PostVisibility.PUBLIC;
        } else {
            this.visibility = visibility;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null) {
            this.content = "";
        } else {
            this.content = content;
        }
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        if (likes == null) {
            this.likes = 0;
        } else {
            this.likes = likes;
        }
    }

    public Integer getTimesSaved() {
        return timesSaved;
    }

    public void setTimesSaved(Integer timesSaved) {
        if (timesSaved == null) {
            this.timesSaved = 0;
        } else {
            this.timesSaved = timesSaved;
        }
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Post> getComments() {
        return comments;
    }

    public void setComments(Set<Post> comments) {
        this.comments.addAll(comments);
    }

    public Set<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }

    public Set<User> getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(Set<User> savedBy) {
        this.savedBy = savedBy;
    }
    
    // <<-CLASS->>
    static class TreePost extends java.util.TreeSet<Post> {
        
        // <<-CONSTANT->>
        private static final long serialVersionUID = 1L;

        // <<-CONSTRUCTOR->>
        public TreePost() {
            // The argument is a Comparator
            super((p1, p2) -> p1.getPostDate().compareTo(p2.getPostDate()));
        }
        
    }
    
}
