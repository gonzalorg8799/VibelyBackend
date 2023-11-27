package com.metrica.vibely.data.service.impl;
	
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
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
		User user = UserMapper.toEntity(userParam, null, null, null, null, null, null);
		
		user.setState		(UserState.ENABLED);
		user.setStatus		(UserStatus.ONLINE);
		user.setLogins		(1);
		user.setLastConnDate(LocalDateTime.now());
        user.setPassword	(userParam.getPassword());
        
		return UserMapper.toDTO(userRepository.save(user));
	}

	
	public UserDTO update(UserDTO newUserDto) {
		newUserDto = updateNickname(newUserDto.getUserId(), newUserDto.getNickname());
		newUserDto = updateUsername(newUserDto.getUserId(), newUserDto.getUsername());
		newUserDto = updateEmail(newUserDto.getUserId(), newUserDto.getEmail());
		newUserDto = updatePassword(newUserDto.getUserId(), newUserDto.getPassword());
		newUserDto = updateStatus(newUserDto.getUserId(), newUserDto.getStatus());
		newUserDto = updatePrivacy(newUserDto.getUserId(), newUserDto.getPrivacy());
		
		User user = userRepository.findById(newUserDto.getUserId()).orElseThrow();
		
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

	@Override
	public UserDTO getById(UUID id) {
		return UserMapper.toDTO(userRepository.findById(id).get());
	}

	@Override
	public void deleteById(UUID id) {
		userRepository.deleteById(id);
	}
	
	private UserDTO updateUsername(final UUID userId, final String username) {
		User user = userRepository.findById(userId)
				.orElseThrow();
		
		if(username != null && !username.equals(user.getUsername())) { user.setUsername(username); } 
		
		return UserMapper.toDTO(user);
	}
	
	private UserDTO updateNickname(final UUID userId, final String nickname) {
		User user = userRepository.findById(userId)
				.orElseThrow();
		
		if(nickname != null && !nickname.equals(user.getNickname())) { user.setNickname(nickname); }  
		
		return UserMapper.toDTO(user);
	}
	
	private UserDTO updateEmail(final UUID userId, final String email) {
		User user = userRepository.findById(userId)
				.orElseThrow();
		
		if(email != null && !email.equals(user.getEmail())) { user.setEmail(email); } 
		
		return UserMapper.toDTO(user);
	}
	
	private UserDTO updatePassword(final UUID userId, final String password) {
		User user = userRepository.findById(userId)
				.orElseThrow();
		
		if(password != null && !password.equals(user.getPassword())) { user.setPassword(password); } 
		
		return UserMapper.toDTO(user);
	}
	private UserDTO updateStatus(final UUID userId, final UserStatus status) {
		User user = userRepository.findById(userId).orElseThrow();
		if(status != null) {
			user.setStatus(status);
		}
		
		return UserMapper.toDTO(userRepository.save(user));
	}
	private UserDTO updatePrivacy(final UUID userID, final PrivacyType privacy) {
		User user = userRepository.findById(userID).orElseThrow();
		if(privacy != null) {
			user.setPrivacy(privacy);			
		}
		return UserMapper.toDTO(userRepository.save(user));
	}
}
