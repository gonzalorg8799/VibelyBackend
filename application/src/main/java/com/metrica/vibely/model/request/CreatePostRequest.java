package com.metrica.vibely.model.request;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.PostDTO;
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
	private String content;

	@NotNull
	@NotBlank
	private UUID owner;
	
//	<<--CONSTRUCTOR-->>
	public CreatePostRequest() {
		
	}

// 	<<--METHODS-->>
	public static PostDTO toPostDTO(CreatePostRequest post) {
		PostDTO postDto = new PostDTO();
		postDto.setContent   (post.getContent());
		postDto.setOwner	 (post.getOwner());
		return postDto;
	}

//	<<--GETTERS & SETTERS-->>
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	
}
