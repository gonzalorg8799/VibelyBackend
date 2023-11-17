package com.metrica.vibely.data.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
 * <h1>Post Entity</h1>
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
	@Column(name = "post_id")
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
            unique = true,
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_post_user"))
    private User owner;
    @OneToMany(mappedBy = "postId")
	private Set<Post> comments;
    @OneToMany(mappedBy = "userId")
    private Set<User> likedBy;
    @OneToMany(mappedBy = "userId")
    private Set<User> savedBy;
    
    // <<-CONSTRUCTORS->>
	public Post() {
	    this.setPostId(null);
	    this.setComments(null);
        this.setLikedBy(null);
        this.setSavedBy(null);
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
            Set<User> savedBy) {
        this.setPostId(postId);
        this.setPostDate(postDate);
        this.setStatus(status);
        this.setVisibility(visibility);
        this.setContent(content);
        this.setLikes(likes);
        this.setTimesSaved(timesSaved);
        this.setOwner(owner);
        this.setComments(comments);
        this.setLikedBy(likedBy);
        this.setSavedBy(savedBy);
    }
    
    /**
     * Constructs a copy of the given entity.
     * 
     * @param post the post to copy
     */
    public Post(Post post) {
        this.setPostId    (post.getPostId());
        this.setPostDate  (post.getPostDate());
        this.setStatus    (post.getStatus());
        this.setVisibility(post.getVisibility());
        this.setContent   (post.getContent());
        this.setLikes     (post.getLikes());
        this.setTimesSaved(post.getTimesSaved());
        this.setOwner     (post.getOwner());
        this.setComments  (post.getComments());
        this.setLikedBy   (post.getLikedBy());
        this.setSavedBy   (post.getSavedBy());
    }

    // <<-METHODS->>
    @Override
    public int hashCode() {
        return Objects.hash(this.postId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        return Objects.equals(this.postId, other.postId);
    }
    
    // <<-GETTERS & SETTERS->>
    public UUID getPostId() {
        return this.postId;
    }

    public void setPostId(final UUID postId) {
        if (postId == null) {
            this.postId = UUID.randomUUID();
        } else {
            this.postId = postId;
        }
        // Another way
        // this.postId = (postId == null) ? UUID.randomUUID() : postId;
    }

    public LocalDateTime getPostDate() {
        return this.postDate;
    }

    public void setPostDate(final LocalDateTime postDate) {
        if (postDate == null) {
            this.postDate = LocalDateTime.now();
        } else {
            this.postDate = postDate;
        }
    }

    public PostStatus getStatus() {
        return this.status;
    }

    public void setStatus(final PostStatus status) {
        if (status == null) {
            this.status = PostStatus.ACTIVE;
        } else {
            this.status = status;
        }
    }

    public PostVisibility getVisibility() {
        return this.visibility;
    }

    public void setVisibility(final PostVisibility visibility) {
        if (visibility == null) {
            this.visibility = PostVisibility.PUBLIC;
        } else {
            this.visibility = visibility;
        }
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        if (content == null) {
            this.content = "";
        } else {
            this.content = content;
        }
    }

    public Integer getLikes() {
        return this.likes;
    }

    public void setLikes(final Integer likes) {
        if (likes == null) {
            this.likes = 0;
        } else {
            this.likes = likes;
        }
    }

    public Integer getTimesSaved() {
        return this.timesSaved;
    }

    public void setTimesSaved(final Integer timesSaved) {
        if (timesSaved == null) {
            this.timesSaved = 0;
        } else {
            this.timesSaved = timesSaved;
        }
    }

    public User getOwner() {
        return new User(this.owner);
    }

    public void setOwner(final User owner) {
        this.owner = new User(owner);
    }

    public Set<Post> getComments() {
        return this.comments.stream()
                .map(Post::new)
                .collect(Collectors.toSet());
    }

    public void setComments(final Set<Post> comments) {
        this.comments = new TreePost();
        if (comments != null) {
            this.comments.addAll(comments.stream()
                    .map(Post::new)
                    .collect(Collectors.toSet()));
        }
    }

    public Set<User> getLikedBy() {
        return this.likedBy.stream()
                .map(User::new)
                .collect(Collectors.toSet());
    }

    public void setLikedBy(final Set<User> likedBy) {
        this.likedBy = new HashSet<>();
        if (likedBy != null) {
            this.likedBy.addAll(likedBy.stream()
                    .map(User::new)
                    .collect(Collectors.toSet()));
        }
    }

    public Set<User> getSavedBy() {
        return this.savedBy.stream()
                .map(User::new)
                .collect(Collectors.toSet());
    }

    public void setSavedBy(final Set<User> savedBy) {
        this.savedBy = savedBy;
        if (savedBy != null) {
            this.savedBy.addAll(savedBy.stream()
                    .map(User::new)
                    .collect(Collectors.toSet()));
        }
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
