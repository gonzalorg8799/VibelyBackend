package com.metrica.vibely.data.model.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;

/**
 * @since 2023-11-14
 * @author Adrian
 * @version 1.0
 *
 */
public class UserMapper {

	public static User toEntity(UserDTO userDTO, Set<User> followers, Set<User> following,  Set<Post> posts, Set<Chat> chats) {
		User user = new User();
		
		// Mapping Basics
		user.setUserId		(userDTO.getUserId());
		user.setNickname	(userDTO.getNickname());
		user.setUsername	(userDTO.getUsername());
		user.setPassword	(userDTO.getPassword());
		user.setEmail		(userDTO.getEmail());
		user.setApikey		(userDTO.getApikey());
		user.setPrivacy		(userDTO.getPrivacy());
		user.setStatus		(userDTO.getStatus());
		user.setState		(userDTO.getState());
		user.setLogins		(userDTO.getLogins());
		user.setBlockedDate (userDTO.getBlockedDate());
		user.setLastConnDate(userDTO.getLastConnDate());
		
		// Mapping Relations
		user.setFollowers	(followers);
		user.setFollowing	(following);
		user.setPosts		(posts);
		user.setChats	 	(chats);
		
		return user;
	}
	
	public static UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();

		// Mapping Basics
		userDTO.setUserId	   (user.getUserId());
		userDTO.setNickname	   (user.getNickname());
		userDTO.setUsername	   (user.getUsername());
		userDTO.setPassword	   (user.getPassword());
		userDTO.setEmail	   (user.getEmail());
		userDTO.setApikey	   (user.getApikey());
		userDTO.setPrivacy     (user.getPrivacy());
		userDTO.setStatus	   (user.getStatus());
		userDTO.setState	   (user.getState());
		userDTO.setLogins	   (user.getLogins());
		userDTO.setBlockedDate (user.getBlockedDate());
		userDTO.setLastConnDate(user.getLastConnDate());
		
		// Mapping Relations        
		userDTO.setFollowers   (user.getFollowers().stream()  // Mapping to get only the id
				                    .map(User::getUserId)
				                    .collect(Collectors.toSet()));   
		
		userDTO.setFollowing   (user.getFollowing().stream() 
				                    .map(User::getUserId)
				                    .collect(Collectors.toSet()));   
		
		userDTO.setPosts   	   (user.getPosts().stream() 
					                 .map(Post::getPostId)
					                .collect(Collectors.toSet()));  
		
		userDTO.setChats	   (user.getChats().stream()
							 	    .map(Chat::getChatId)
							 	    .collect(Collectors.toSet()));
		
		return userDTO;
	}
}
