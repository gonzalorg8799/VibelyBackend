package com.metrica.vibely.data.service;

import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;


/**
 * <h1>Post Service</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Daniel,
 */
public interface PostService {

	/**
	 * gets post information by its id
	 * @param postId
	 * @return PostDTO
	 * @throws NoSuchElementException
	 */
	PostDTO getById(UUID postId);	
	
	/**
	 * creates a new post and adds it to the database
	 * @param postDTO
	 * @throws NoSuchElementException
	 */
	PostDTO create(PostDTO postDTO); 
	
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
	PostDTO ChangeStatus(PostStatus status); 
	
	/**
	 * changes post visibility
	 * @param visibility
	 * @throws NoSuchElementException
	 */
	PostDTO ChangeVisibility(PostVisibility visibility); 
	
	/**
	 * deletes a post given its Id
	 * @throws NoSuchElementException
	 */
	void deleteById(UUID postId);
}
