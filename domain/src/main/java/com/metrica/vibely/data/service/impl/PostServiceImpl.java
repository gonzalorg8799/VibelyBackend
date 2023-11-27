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

	//TODO hacer bien el create
	@Override
	public PostDTO create(PostDTO dto) {
		Post post = PostMapper.toEntity(dto, null, null, null, null);
		
		post.setContent(dto.getContent());
		
		
//		post.setOwner(dto.getOwner());
        
		return PostMapper.toDTO(postRepository.save(post));
	}

	@Override
	public PostDTO update(PostDTO dto) {
		Post post = postRepository.findById(dto.getPostId()).get();
		
		if(dto.getContent() != null) {
			post.setContent(dto.getContent());
		}
		
		return PostMapper.toDTO(this.postRepository.save(post));
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
	

	
	

}
