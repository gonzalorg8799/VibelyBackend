package com.metrica.vibely.data.service.impl;
	
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.State;
import com.metrica.vibely.data.model.enumerator.Status;
import com.metrica.vibely.data.model.mapper.UserMapper;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.UserService;		

/**
 * @since 2023-11-14
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(final UserRepository userRepostory) {
		this.userRepository = userRepostory;
	}
	
	@Override
	public UserDTO getByUsername(final String username) {
		return UserMapper.toDTO(userRepository.findByUsername(username)
											  .orElseThrow());
	}

	@Override
	public void deleteByUsername(final String username) {
	    userRepository.deleteByUsername(username);
	}

	@Override
	public UserDTO create(final UserDTO userParam) {
		User user = UserMapper.toEntity(userParam);
		
		user.setState		(State.ENABLED);
		user.setStatus		(Status.ONLINE);
		user.setLogins		(1);
		user.setLastConnDate(LocalDateTime.now());
		user.setFollowers	(null);
        user.setFollowing	(null);
        user.setChats		(null);
        user.setPassword	(userParam.getPassword());
        
		return UserMapper.toDTO(userRepository.save(user));
	}

	@Override
	public UserDTO updateUsername(final UUID userId, final String username) {
		User user = userRepository.findById(userId)
								  .orElseThrow();

		if(!username.equals(user.getUsername())) { user.setUsername(username); } 

		return UserMapper.toDTO(userRepository.save(user));
	}
	
	public UserDTO updateNickname(final UUID userId, final String nickname) {
		User user = userRepository.findById(userId)
								  .orElseThrow();

		if(!nickname.equals(user.getNickname())) { user.setNickname(nickname); } 
		
		return UserMapper.toDTO(userRepository.save(user));
	}
	
	public UserDTO updateEmail(final UUID userId, final String email) {
		User user = userRepository.findById(userId)
								  .orElseThrow();

		if(!email.equals(user.getEmail())) { user.setEmail(email); } 
		
		return UserMapper.toDTO(userRepository.save(user));
	}
	
	public UserDTO updatePassword(final UUID userId, final String password) {
		User user = userRepository.findById(userId)
								  .orElseThrow();

		if(!password.equals(user.getPassword())) { user.setPassword(password); } 
		
		return UserMapper.toDTO(userRepository.save(user));
	}

	@Override
	public UserDTO followUser(final UUID userId,final UUID followedUserId) {
		User user 		  = userRepository.findById(userId)
										  .orElseThrow();
		User followedUser = userRepository.findById(followedUserId)
										  .orElseThrow();
		
		if(!followedUser.getFollowers().contains(user) && userId != followedUserId) {
			followedUser.getFollowers().add     (user); 
			user		.getFollowing().add		(followedUser); 
			userRepository			   .save	(followedUser);
		} 
		
		return UserMapper.toDTO(userRepository.save(user));
	}

	@Override
	public UserDTO unfollowUser(final UUID userId, final UUID followedUserId) {
		User user 		  	= userRepository.findById(userId)
											.orElseThrow();
		User unFollowedUser = userRepository.findById(followedUserId)
											.orElseThrow();
		
		if( unFollowedUser.getFollowers().contains(user) && userId != followedUserId) {
			unFollowedUser.getFollowers().remove  (user); 
			user		  .getFollowing().remove  (unFollowedUser); 
			userRepository				 .save	  (unFollowedUser);
		} 
		
		return UserMapper.toDTO(userRepository.save(user));
	}
	
}
