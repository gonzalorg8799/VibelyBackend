package com.metrica.vibely.model.request;

import java.time.LocalDateTime;
import java.util.Set;

import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @since 2023-11-20
 * @version 1.0
 */
public class CreatePostRequest {
	
//	<<--FIELDS-->>
	@NotNull
	@NotBlank
	private LocalDateTime postDate;
	
	@NotNull
	@NotBlank
	private PostStatus status;
	
	@NotNull
	@NotBlank
	private PostVisibility visibility;
	
	@NotNull
	@NotBlank
	private String content;
	private Integer likes;
	private Integer timesSaved;
	
	@NotNull
	@NotBlank
	private User owner;
	private Set<Post> comments;
	private Set<User> likedBy;
	private Set<User> savedBy;
	
//	<<--CONSTRUCTOR-->>
	public CreatePostRequest() {
		
	}

// 	<<--METHODS-->>
	public static PostDTO toPostDTO(CreatePostRequest post) {
		PostDTO postDto = new PostDTO();
		
		postDto.setPostDate  (post.getPostDate());
		postDto.setStatus    (post.getStatus());
		postDto.setVisibility(post.getVisibility());
		postDto.setContent   (post.getContent());
		postDto.setLikes	 (post.getLikes());
		postDto.setTimesSaved(post.getTimesSaved());
		postDto.setOwner	 (post.getOwner());
		postDto.setComments  (post.getComments());
		postDto.setLikedBy   (post.getLikedBy());
		postDto.setSavedBy   (post.getSavedBy());
		
		return postDto;
	}

//	<<--GETTERS & SETTERS-->>
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
		this.comments = comments;
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
}
