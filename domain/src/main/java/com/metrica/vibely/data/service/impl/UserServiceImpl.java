package com.metrica.vibely.data.service.impl;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.PrivacyType;
import com.metrica.vibely.data.model.enumerator.UserState;
import com.metrica.vibely.data.model.enumerator.UserStatus;
import com.metrica.vibely.data.model.mapper.UserMapper;
import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.UserService;
import com.metrica.vibely.data.util.PasswordHasher; 
	
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;	

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
        return UserMapper.toDTO(this.userRepository.findById(id).orElseThrow());
    }
	
	@Override
	public UserDTO getByUsername(final String username) {
		return UserMapper.toDTO(this.userRepository.findByUsername(username)
											  .orElseThrow());
	}
	
	@Override
	public UserDTO getByEmail(final String email) {
		return UserMapper.toDTO(this.userRepository.findByEmail(email)
											  .orElseThrow());
	}

	@Override
	public void deleteByUsername(final String username) {
	    this.userRepository.deleteByUsername(username);
	}

	
	@Override
	public UserDTO create(final UserDTO userDTO) {
		User user = UserMapper.toEntity(userDTO);
		
		user.setState		(UserState.ENABLED);
		user.setStatus		(UserStatus.ONLINE);
		user.setLogins		(1);
		user.setLastConnDate(LocalDateTime.now());
        user.setPassword	(PasswordHasher.hash(userDTO.getPassword()));
        
		return UserMapper.toDTO(this.userRepository.save(user));
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		User user = userRepository.findById(userDTO.getUserId()).orElseThrow();
		
		String newNickname     = userDTO.getNickname();
		String newUsername     = userDTO.getUsername();
		String newEmail        = userDTO.getEmail();
		String newPassword     = userDTO.getPassword();
		UserStatus newStatus   = userDTO.getStatus();
		PrivacyType newPrivacy = userDTO.getPrivacy();
		
        if (newNickname != null) {
            user.setNickname(newNickname);
        }
        
        if (newUsername != null) {
            user.setUsername(newUsername);
        }
        
        if (newEmail != null) {
            user.setEmail(newEmail);
        }
        
        if (newPassword != null) {
            user.setNickname(PasswordHasher.hash(newPassword));
        }
        
        if (newStatus != null) {
            user.setStatus(newStatus);
        }
        
        if (newPrivacy != null) {
            user.setPrivacy(newPrivacy);
        }
		
		return UserMapper.toDTO(this.userRepository.save(user));
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

	@Override
	public Map<UUID, Double> getFriendNetwork(UUID id) {
		User user = userRepository.findById(id).orElseThrow();
		Set<UUID> followersIds = user.getFollowers().stream()
									 .map(User::getUserId)
									 .collect(Collectors.toSet());
		Set<UUID> followingIds = user.getFollowing().stream()
				 					 .map(User::getUserId)
				 					 .collect(Collectors.toSet());
		
		Set<UUID> friends = followingIds.stream()
								.filter(f->followersIds.contains(f))
								//.map(m -> userRepository.findById(m).orElseThrow())
								.collect(Collectors.toSet());
		
		Set<Chat>chatsFriends=user.getChats().stream()
				.filter(c->c.getParticipants().stream()
							.filter(f->!user.getUserId().equals(f.getUserId()))
						  	.anyMatch(friends::contains))
				.collect(Collectors.toSet());
		
		Set<Post>friendsComments=user.getPosts().stream()
				.flatMap(p->p.getComments().stream())
				.filter(p->friends.contains(p.getOwner()))
				.collect(Collectors.toSet());
	

		Map<UUID, Integer> messagePoints = chatsFriends.stream()
		        .flatMap(chat -> chat.getMessages().stream())
		        .collect(Collectors.groupingBy(
		                m->m.getSender().getUserId(),
		                Collectors.summingInt(message -> 1)
		        ));
		Map<UUID, Double> commentsPoints = friendsComments.stream()
		        .collect(Collectors.groupingBy(
		                p->p.getOwner().getUserId(),
		                Collectors.summingDouble(post->0.5)
		        ));
		
		// suma de la puntuacion total de cada usuario
		Map<UUID, Double> resultMap = new HashMap<>();
		
        messagePoints.forEach((key, value) -> resultMap.merge(key, value.doubleValue(), Double::sum));
        commentsPoints.forEach((key, value) -> resultMap.merge(key, value, Double::sum));
        Set<Map.Entry<UUID, Double>> setdemapa;
	}


	
}
