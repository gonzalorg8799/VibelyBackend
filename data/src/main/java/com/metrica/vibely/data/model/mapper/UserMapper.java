package com.metrica.vibely.data.model.mapper;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;

import java.util.stream.Collectors;

/**
 * <h1>User Mapper</h1>
 * 
 * @since 2023-11-14
 * @version 1.0
 * @author Adrian
 */
public class UserMapper {

    // <<-METHODS->>
    /**
     * Maps the information contained in the DTO into the Entity.
     * 
     * This method is mainly used when you create a new entity
     * so it's really not important the values you assign to the
     * relations fields.
     * 
     * @param userDTO the DTO to map
     * @return the entity with the DTO info
     */
	public static User toEntity(UserDTO userDTO) {
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
        user.setFollowers(null);
        user.setFollowing(null);
        user.setPosts    (null);
        user.setChats    (null);
        user.setLikes    (null);
        user.setSaves    (null);

        return user;
    }
	
	/**
	 * 
	 * @param user
	 * @return
	 */
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
		userDTO.setFollowers(user.getFollowers() // Mapping to get only the id
                .stream()
                .map(User::getUserId)
                .collect(Collectors.toSet()));
        userDTO.setFollowing(user.getFollowing()
                .stream()
                .map(User::getUserId)
                .collect(Collectors.toSet()));
        userDTO.setPosts(user.getPosts()
                .stream()
                .map(Post::getPostId)
                .collect(Collectors.toSet()));
        userDTO.setChats(user.getChats()
                .stream()
                .map(Chat::getChatId)
                .collect(Collectors.toSet()));
        userDTO.setLikes(user.getPosts()
                .stream()
                .map(Post::getPostId)
                .collect(Collectors.toSet()));
        userDTO.setSaves(user.getSaves()
                .stream()
                .map(Post::getPostId)
                .collect(Collectors.toSet()));

        return userDTO;
    }
	
}