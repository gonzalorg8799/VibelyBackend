package com.metrica.vibely.model.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * <h1>Get POst Response</h1>
 * 
 * @since 2023-11-28
 * @version 1.0
 * @author Adri
 */
public class GetPostResponse {
	
    // <<-FIELDS->>
    private UUID postId;
    private LocalDateTime postDate;
    private PostStatus status;
    private PostVisibility visibility;
    private String content;
    private Integer likes;
    private Integer timesSaved;
    
    // <<-CONSTRUCTOR->>
    public GetPostResponse() {
    }
    
    // <<-METHOD->>
    public GetPostResponse generateResponse(PostDTO dto) {
        this.setPostId    (dto.getPostId());
        this.setPostDate  (dto.getPostDate());
        this.setStatus 	  (dto.getStatus());
        this.setContent   (dto.getContent());
        this.setLikes     (dto.getLikes());
        this.setTimesSaved(dto.getTimesSaved());
        
        return this;
    }

    // <<-GETTERS & SETTERS->>
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
    
    
    
}
