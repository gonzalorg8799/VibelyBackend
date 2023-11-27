package com.metrica.vibely.data.service.impl;
	
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.model.mapper.UserMapper;
import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
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
		user.setFollowers	(null);
        user.setFollowing	(null);
        user.setChats		(null);
        user.setPassword	(userParam.getPassword());
        
		return UserMapper.toDTO(userRepository.save(user));
	}

	private UserDTO updateUsername(final UUID userId, final String username) {
		User user = userRepository.findById(userId)
								  .orElseThrow();

		if(!username.equals(user.getUsername()) && username != null) { user.setUsername(username); } 

		return UserMapper.toDTO(user);
	}
	
	private UserDTO updateNickname(final UUID userId, final String nickname) {
		User user = userRepository.findById(userId)
								  .orElseThrow();

		if(!nickname.equals(user.getNickname()) && nickname != null) { user.setNickname(nickname); }  
		
		return UserMapper.toDTO(user);
	}
	
	private UserDTO updateEmail(final UUID userId, final String email) {
		User user = userRepository.findById(userId)
								  .orElseThrow();

		if(!email.equals(user.getEmail()) && email != null) { user.setEmail(email); } 
		
		return UserMapper.toDTO(user);
	}
	
	private UserDTO updatePassword(final UUID userId, final String password) {
		User user = userRepository.findById(userId)
								  .orElseThrow();

		if(!password.equals(user.getPassword()) && password != null) { user.setPassword(password); } 
		
		return UserMapper.toDTO(user);
	}
	
	public UserDTO update(UserDTO newUserDto) {
		UserDTO userDto = UserMapper.toDTO(userRepository.findById(newUserDto.getUserId()).get()) ; 
		

		userDto.setNickname(updateNickname(userDto.getUserId(), newUserDto.getNickname()).getNickname());
		userDto.setUsername(updateUsername(userDto.getUserId(), newUserDto.getUsername()).getUsername());
		userDto.setEmail   (updateEmail   (userDto.getUserId(), newUserDto.getEmail())   .getEmail());
		userDto.setPassword(updatePassword(userDto.getUserId(), newUserDto.getPassword()).getPassword());
		
		User user = userRepository.findById(newUserDto.getUserId()).get();
		
		Set<User> followers = user.getFollowers();
		Set<User> following = user.getFollowing();
		Set<Post> posts = user.getPosts();
		Set<Chat> chats = user.getChats();
		Set<Post> likes = user.getLikes();
		Set<Post> saves = user.getSaves();
		
		return UserMapper.toDTO(userRepository.save(UserMapper.toEntity(userDto, followers, following, posts, chats, likes, saves))); 

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        
    }
	
}
