package com.metrica.vibely.data.service.impl;
	
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.model.mapper.UserMapper;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.UserService;
import com.metrica.vibely.data.util.PasswordHasher;		

/**
 * @since 2023-11-14
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
	
    // <<-FIELD->>
	private UserRepository userRepository;

    // <<-CONSTRUCTOR->>
	@Autowired
	public UserServiceImpl(final UserRepository userRepostory) {
		this.userRepository = userRepostory;
	}

    // <<-METHODS->>
    @Override
    public UserDTO getById(UUID id) {
        return UserMapper.toDTO(userRepository.findById(id).orElseThrow());
    }
	
	@Override
	public UserDTO getByUsername(final String username) {
		return UserMapper.toDTO(userRepository.findByUsername(username)
											  .orElseThrow());
	}
	
	@Override
	public UserDTO getByEmail(final String email) {
		return UserMapper.toDTO(userRepository.findByEmail(email)
											  .orElseThrow());
	}

	@Override
	public void deleteByUsername(final String username) {
		User user = this.userRepository.findByUsername(username).orElseThrow();
        user.setState(UserState.DISABLED);
        this.userRepository.save(user);
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
		User user = userRepository.findById(newUserDto.getUserId()).orElseThrow();
		
		String newNickname = newUserDto.getNickname();
		String newUsername = newUserDto.getUsername();
		String newEmail = newUserDto.getEmail();
		String newPassword = newUserDto.getPassword();
		UserStatus newStatus = newUserDto.getStatus();
		PrivacyType newPrivacy = newUserDto.getPrivacy();
		
		if(newNickname != null) { user.setNickname(newNickname); }
		if(newUsername != null) { user.setUsername(newUsername); }
		if(newEmail    != null) { user.setEmail(newEmail); }
		if(newPassword != null) { user.setNickname(PasswordHasher.hash(newPassword)); }
		if(newStatus   != null) { user.setStatus(newStatus); }
		if(newPrivacy  != null) { user.setPrivacy(newPrivacy); }
		
		
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
	public void deleteById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow();
        user.setState(UserState.DISABLED);
        this.userRepository.save(user);
	}
	
}
