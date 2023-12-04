package com.metrica.vibely.data.service.impl;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.model.enumerator.PostStatus;
import com.metrica.vibely.data.model.enumerator.PostVisibility;
import com.metrica.vibely.data.model.mapper.ChatMapper;
import com.metrica.vibely.data.model.mapper.PostMapper;
import com.metrica.vibely.data.repository.PostRepository;
import com.metrica.vibely.data.repository.UserRepository;
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
	private UserRepository userRepository;
	
	@Autowired
	public PostServiceImpl(final PostRepository postRepository, final UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	public PostDTO getById(UUID id) {
		return PostMapper.toDTO(postRepository.findById(id)
				  							  .orElseThrow());
	}

	@Override
	public PostDTO create(PostDTO dto) {
		Post post = PostMapper.toEntity(dto, null, null, null, null);
		User owner = this.userRepository.findById(dto.getOwner()).orElseThrow();
		
		post.setPostDate(LocalDateTime.now());
		post.setStatus(PostStatus.ACTIVE);
		post.setVisibility(PostVisibility.PUBLIC);
		post.setContent(dto.getContent());
		post.setLikes(null);
		post.setTimesSaved(null);
		post.setOwner(owner);
        
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

	@Override
	public PostDTO addLikedBy(PostDTO postDTO) {
        Post post = this.postRepository.findById(postDTO.getPostId()).orElseThrow();
        
        Set<UUID> likedBySet = postDTO.getLikedBy();
        if(likedBySet.isEmpty()) {
        	throw new NoSuchElementException();
        }
        UUID userId = likedBySet.stream().findFirst().get();
        User user = this.userRepository.findById(userId).orElseThrow();
        
        Set<User> userList = post.getLikedBy();  
        if(!userList.contains(user)) {
            post.setLikes(post.getLikes() + 1);
        }
        userList.add(user);
        post.setLikedBy(userList);
        
        return PostMapper.toDTO(this.postRepository.save(post));
	}
	
	@Override
	public PostDTO removeLikedBy(PostDTO postDTO) {
        Post post = this.postRepository.findById(postDTO.getPostId()).orElseThrow();
        
        Set<UUID> dislikedBySet = postDTO.getLikedBy(); // in this case is a dislike
        if(dislikedBySet.isEmpty()) {
        	throw new NoSuchElementException();
        }
        UUID userId = dislikedBySet.stream().findFirst().get();
        User user = this.userRepository.findById(userId).orElseThrow();
        
        Set<User> userList = post.getLikedBy();  
        if(userList.contains(user)) {
            post.setLikes(post.getLikes() - 1);
            userList.remove(user);
        }
        post.setLikedBy(userList);
        
        return PostMapper.toDTO(this.postRepository.save(post));
	}
	

	
	

}
