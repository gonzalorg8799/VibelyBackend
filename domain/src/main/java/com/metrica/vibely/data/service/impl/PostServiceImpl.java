package com.metrica.vibely.data.service.impl;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;
import com.metrica.vibely.data.model.mapper.PostMapper;
import com.metrica.vibely.data.repository.PostRepository;
import com.metrica.vibely.data.service.PostService;

/**
 * <h1>Post Service Impl</h1>
 * 
 * @since 2023-11-27
 * @author Adri
 * @version 1.0
 */
@Service
public class PostServiceImpl implements PostService{
	
	private PostRepository postRepository;
	
	@Autowired
	public PostServiceImpl(final PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public PostDTO getById(UUID id) {
		return PostMapper.toDTO(postRepository.findById(id)
				  							  .orElseThrow());
	}

	@Override
	public PostDTO create(PostDTO dto) {
		Post post = PostMapper.toEntity(dto, null, null, null, null);
		
		post.setPostDate  (LocalDateTime.now());
		post.setStatus	  (PostStatus.ACTIVE);
		post.setVisibility(PostVisibility.PUBLIC);
		post.setContent	  (null);
		post.setLikes	  (null);
        post.setTimesSaved(null);
        
		return PostMapper.toDTO(postRepository.save(post));
	}

	@Override
	public PostDTO update(PostDTO dto) {
		Post post = postRepository.findById(dto.getPostId()).get();
		PostDTO postDTO = PostMapper.toDTO(post) ; 
		
		postDTO.setContent(updateContent(postDTO.getPostId(), postDTO.getContent()).getContent());
		
		Post postUpdated = PostMapper.toEntity(postDTO, post.getOwner(), post.getComments(), post.getLikedBy(), post.getSavedBy());
		
		postRepository.save(postUpdated);
		
		return postDTO;
	}

	@Override
	public PostDTO getByContentandCreator(String content, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO changeStatus(PostStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO changeVisibility(PostVisibility visibility) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID postId) {
		postRepository.deleteById(postId);
	}
	
	private PostDTO updateContent(final UUID postId, final String content) {
		Post post = postRepository.findById(postId)
								     .orElseThrow();

		if(content != null && content.equals(post.getContent())) { 
			post.setContent(content); 
		}  
		
		return PostMapper.toDTO(post);
	}

	
	

}
