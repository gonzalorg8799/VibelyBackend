package com.metrica.vibely.data.service.impl;
	
import java.time.LocalDateTime;
import java.util.Set;
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
	public UserServiceImpl(UserRepository userRepostory) {
		this.userRepository = userRepostory;
	}
	
	@Override
	public UserDTO getByUsername(String username) {
		return UserMapper.toDTO(userRepository.findByUsername(username)
											  .orElseThrow());
	}

	@Override
	public void deleteByUsername(String username) {
	    userRepository.deleteByUsername(username);
	}

	@Override
	public UserDTO create(UserDTO userParam) {
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
	public UserDTO updateUsername(UUID userId, String username) {
		User user = userRepository.findByUserId(userId).orElseThrow();

		if(!username.equals(user.getUsername())) { user.setUsername(username); } 

		return UserMapper.toDTO(userRepository.save(user));
	}
	
	public UserDTO updateNickname(UUID userId, String nickname) {
		User user = userRepository.findByUserId(userId).orElseThrow();

		if(!nickname.equals(user.getNickname())) { user.setNickname(nickname); } 
		
		return UserMapper.toDTO(userRepository.save(user));
	}
	
	public UserDTO updateEmail(UUID userId, String email) {
		User user = userRepository.findByUserId(userId).orElseThrow();

		if(!email.equals(user.getEmail())) { user.setEmail(email); } 
		
		return UserMapper.toDTO(userRepository.save(user));
	}
	
	public UserDTO updatePassword(UUID userId, String password) {
		User user = userRepository.findByUserId(userId).orElseThrow();

		if(!password.equals(user.getPassword())) { user.setPassword(password); } 
		
		return UserMapper.toDTO(userRepository.save(user));
	}

	@Override
	public UserDTO followUser(UUID userId, UUID follwedUserId, String username) {
		User user 		  = userRepository.findByUserId(userId).orElseThrow();
		User followedUser = userRepository.findByUserId(follwedUserId).orElseThrow();
		
		Set<User> userFollowed 		= user.getFollowing();
		Set<User> followedUserFollowers = followedUser.getFollowers();
		
		if(!followedUser.getFollowers().contains(userId)) {
			userFollowed.add(followedUser); 
			followedUserFollowers.add(user); 
			userRepository.save(followedUser);
		} 
		
		return UserMapper.toDTO(userRepository.save(user));
	}
	
}
