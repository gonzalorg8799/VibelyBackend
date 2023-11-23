package com.metrica.vibely.data.model.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.metrica.vibely.data.entity.Admin;
import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.AdminDTO;

/**
 * @since 2023-11-22
 * @author Adrian
 * @version 1.0
 *
 */
public class AdminMapper {

	public static Admin toEntity(AdminDTO adminDTO, Set<User> followers, Set<User> following,  Set<Post> posts, Set<Chat> chats) {
        Admin admin = new Admin();

        // Mapping Basics
        admin.setUserId	 	 (adminDTO.getUserId());
        admin.setUsername	 (adminDTO.getUsername());
        admin.setPassword	 (adminDTO.getPassword());
        admin.setNickname    (adminDTO.getNickname());
        admin.setEmail		 (adminDTO.getEmail());
        admin.setApikey		 (adminDTO.getApikey());
        admin.setState		 (adminDTO.getState());
        admin.setPrivacy	 (adminDTO.getPrivacy());
        admin.setLogins		 (adminDTO.getLogins());
        admin.setStatus		 (adminDTO.getStatus());
        admin.setLastConnDate(adminDTO.getLastConnDate());
        admin.setBlockedDate (adminDTO.getBlockedDate());

        // Mapping Relations
        admin.setFollowers   (followers);
        admin.setFollowing   (following);
        admin.setPosts	     (posts);
        admin.setChats       (chats);

        return admin;
    }

    public static AdminDTO toDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();

        // Mapping Basics
        adminDTO.setUserId		(admin.getUserId());
        adminDTO.setUsername	(admin.getUsername());
        adminDTO.setPassword	(admin.getPassword());
        adminDTO.setNickname	(admin.getNickname());
        adminDTO.setEmail		(admin.getEmail());
        adminDTO.setApikey		(admin.getApikey());
        adminDTO.setState		(admin.getState());
        adminDTO.setPrivacy		(admin.getPrivacy());
        adminDTO.setLogins		(admin.getLogins());
        adminDTO.setStatus		(admin.getStatus());
        adminDTO.setLastConnDate(admin.getLastConnDate());
        adminDTO.setBlockedDate (admin.getBlockedDate());

        // Mapping Relations
        adminDTO.setFollowers	(admin.getFollowers().stream()
					               	  .map(User::getUserId)
					                  .collect(Collectors.toSet()));

        adminDTO.setFollowing	(admin.getFollowing().stream()
					                  .map(User::getUserId)
					                  .collect(Collectors.toSet()));

        adminDTO.setPosts	 	(admin.getPosts().stream()
					                  .map(Post::getPostId)
					                  .collect(Collectors.toSet()));

        adminDTO.setChats	 	(admin.getChats().stream()
					                  .map(Chat::getChatId)
					                  .collect(Collectors.toSet()));

        return adminDTO;
    }
}
