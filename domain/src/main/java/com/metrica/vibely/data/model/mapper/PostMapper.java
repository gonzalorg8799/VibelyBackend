package com.metrica.vibely.data.model.mapper;

import java.util.stream.Collectors;

import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.PostDTO;

/**
 * @since 2023-11-22
 * @author Adrian
 * @version 1.0
 *
 */
public class PostMapper {

	public static Post toEntity(PostDTO postDTO) {
        Post post = new Post();
        
        // Mapping Basics
        post.setPostId(postDTO.getPostId());
        post.setPostDate(postDTO.getPostDate());
        post.setStatus(postDTO.getStatus());
        post.setVisibility(postDTO.getVisibility());
        post.setContent(postDTO.getContent());
        post.setLikes(postDTO.getLikes());
        post.setTimesSaved(postDTO.getTimesSaved());

     	// Mapping Relations
        post.setOwner(new User(postDTO.getOwner())); //Create User constructor only with the id
        
        post.setComments(postDTO.getComments().stream() // Mapping to get a new Object with that id
				                .map(Post::new) 
				                .collect(Collectors.toSet()));
        
        post.setLikedBy (postDTO.getLikedBy().stream()
				                .map(User::new) 
				                .collect(Collectors.toSet()));
        
        post.setSavedBy (postDTO.getSavedBy().stream()
				                .map(User::new) 
				                .collect(Collectors.toSet()));

        return post;
    }

    public static PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        // Mapping Basics
        postDTO.setPostId(post.getPostId());
        postDTO.setPostDate(post.getPostDate());
        postDTO.setStatus(post.getStatus());
        postDTO.setVisibility(post.getVisibility());
        postDTO.setContent(post.getContent());
        postDTO.setLikes(post.getLikes());
        postDTO.setTimesSaved(post.getTimesSaved());

        // Mapping Relations
        postDTO.setOwner   (post.getOwner().getUserId()); // Getting only the id
        
        postDTO.setComments(post.getComments().stream()  // Mapping to get only the id
				                .map(Post::getPostId)
				                .collect(Collectors.toSet()));        
        
        postDTO.setLikedBy (post.getLikedBy().stream()
				        		.map(User::getUserId)
				        		.collect(Collectors.toSet()));
        
        postDTO.setSavedBy (post.getSavedBy().stream()
				        		.map(User::getUserId)
				        		.collect(Collectors.toSet()));

        return postDTO;
    }
}
