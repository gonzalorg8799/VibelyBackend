package com.metrica.vibely.data.service;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;


/**
 * <h1>Post Service</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Daniel,
 */
public interface PostService extends CrudService<PostDTO, UUID> {
	/**
	 * 
	 * @param content 
	 * @param CreatorUsername
	 * @return postDTO
	 * @throws NoSuchElementException
	 */
	PostDTO getByContentandCreator(String content, String username);
	
	/**
	 * changes post status
	 * @param status
	 * @throws NoSuchElementException
	 */
	PostDTO changeStatus(PostStatus status); 
	
	/**
	 * changes post visibility
	 * @param visibility
	 * @throws NoSuchElementException
	 */
	PostDTO changeVisibility(PostVisibility visibility); 

	void deleteById(UUID postId);

	/**
	 * add user to likedBy list
	 * @param postDTO
	 * @throws NoSuchElementException
	 */
	PostDTO addLikedBy(PostDTO postDTO);

	/**
	 * remove user to likedBy list
	 * @param postDTO
	 * @throws NoSuchElementException
	 */
	PostDTO removeLikedBy(PostDTO postDTO);

	/**
	 * add user to savedBy list
	 * @param postDTO
	 * @throws NoSuchElementException
	 */
	PostDTO addSavedBy(PostDTO postDTO);
	
	/**
	 * remove user to savedBy list
	 * @param postDTO
	 * @throws NoSuchElementException
	 */
	PostDTO removeSavedBy(PostDTO postDTO);
}
