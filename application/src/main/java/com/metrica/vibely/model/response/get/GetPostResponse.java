package com.metrica.vibely.model.response.get;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;

public class GetPostResponse {
	
//	<<--ATRIBUTES-->>
    private UUID           postId;
    private LocalDateTime  postDate;
    
    private PostStatus     status;
    private PostVisibility visibility;
    
    private String         content;
    private Integer        likes;
    private Integer        timesSaved;
    
    private UUID           owner;
    
    private Set<UUID>      comments;
    private Set<UUID>      likedBy;
    private Set<UUID>      savedBy;
	
    
//	<<-CONSTRUCTORS->>
    public GetPostResponse() {
	}

//	<<--METHODS-->>
    public GetPostResponse generateResponse(PostDTO postDto) {
    	
    	this.setPostId(postDto.getPostId());
    	this.setPostDate(postDto.getPostDate());
    	this.setStatus(postDto.getStatus());
    	this.setVisibility(postDto.getVisibility());
    	this.setContent(postDto.getContent());
    	this.setLikes(postDto.getLikes());
    	this.setTimesSaved(postDto.getTimesSaved());
    	this.setOwner(postDto.getOwner());
    	this.setComments(postDto.getComments());
    	this.setLikedBy(postDto.getLikedBy());
    	this.setSavedBy(postDto.getSavedBy());
    	
    	return this;
    }
    
// 	<<--GETTERS & SETTERS-->>
	public UUID getPostId() {
		return postId;
	}

	public void setPostId(UUID postId) {
		this.postId = postId;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	public PostVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(PostVisibility visibility) {
		this.visibility = visibility;
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
		this.likes = likes;
	}

	public Integer getTimesSaved() {
		return timesSaved;
	}

	public void setTimesSaved(Integer timesSaved) {
		this.timesSaved = timesSaved;
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public Set<UUID> getComments() {
		return comments;
	}

	public void setComments(Set<UUID> comments) {
		this.comments = comments;
	}


	public Set<UUID> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Set<UUID> likedBy) {
		this.likedBy = likedBy;
	}


	public Set<UUID> getSavedBy() {
		return savedBy;
	}

	public void setSavedBy(Set<UUID> savedBy) {
		this.savedBy = savedBy;
	}
    
    
}
