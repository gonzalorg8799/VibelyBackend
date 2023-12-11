package com.metrica.vibely.data.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;


/**
 * <h1>Post DTO</h1>
 * 
 * @since 2023-11-20
 * @version 1.0 
 * @author Adri
 *
 */
public class PostDTO {

	// <<-FIELDS->>
    
    // Basics
    private UUID           postId;
    private LocalDateTime  postDate;
    private PostStatus     status;
    private PostVisibility visibility;
    private String         content;
    private Integer        likes;
    private Integer        timesSaved;
    
	// Relations
    private UUID           owner;
    private Set<UUID>      comments;
    private Set<UUID>      likedBy;
    private Set<UUID>      savedBy;
	
    
    // <<-CONSTRUCTORS->>
    public PostDTO() {
	}
    
    public PostDTO(
    		UUID postId,
    		LocalDateTime postDate, 
    		PostStatus status, 
    		PostVisibility visibility,
    		String content,
			Integer likes, 
			Integer timesSaved,
			UUID owner, 
			Set<UUID> comments, 
			Set<UUID> likedBy,
			Set<UUID> savedBy) 
    {
		this.postId = postId;
		this.postDate = postDate;
		this.status = status;
		this.visibility = visibility;
		this.content = content;
		this.likes = likes;
		this.timesSaved = timesSaved;
		this.owner = owner;
		this.comments = comments;
		this.likedBy = likedBy;
		this.savedBy = savedBy;
	}




	// <<-GETTERS & SETTERS->>
	public UUID getPostId() {
		return this.postId;
	}


	public void setPostId(final UUID postId) {
        this.postId = postId;
	}


	public LocalDateTime getPostDate() {
		return this.postDate;
	}


	public void setPostDate(final LocalDateTime postDate) {
		this.postDate = postDate;
	}


	public PostStatus getStatus() {
		return this.status;
	}


	public void setStatus(final PostStatus status) {
		this.status = status;
	}


	public PostVisibility getVisibility() {
		return this.visibility;
	}


	public void setVisibility(final PostVisibility visibility) {
		this.visibility = visibility;
	}


	public String getContent() {
		return this.content;
	}


	public void setContent(final String content) {
		this.content = content;
	}


	public Integer getLikes() {
		return this.likes;
	}


	public void setLikes(final Integer likes) {
		this.likes = likes;
	}


	public Integer getTimesSaved() {
		return this.timesSaved;
	}


	public void setTimesSaved(final Integer timesSaved) {
		this.timesSaved = timesSaved;
	}


	public UUID getOwner() {
		return this.owner;
	}


	public void setOwner(final UUID owner) {
		this.owner = owner;
	}


	public Set<UUID> getComments() {
		return this.comments;
	}


	public void setComments(final Set<UUID> comments) {
		this.comments = comments;
	}


	public Set<UUID> getLikedBy() {
		return this.likedBy;
	}


	public void setLikedBy(final Set<UUID> likedBy) {
		this.likedBy = likedBy;
	}


	public Set<UUID> getSavedBy() {
		return this.savedBy;
	}


	public void setSavedBy(final Set<UUID> savedBy) {
		this.savedBy = savedBy;
	}
    
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
        PostDTO other = (PostDTO) obj;
        return Objects.equals(this.postId, other.postId);
    }

    
    
}
