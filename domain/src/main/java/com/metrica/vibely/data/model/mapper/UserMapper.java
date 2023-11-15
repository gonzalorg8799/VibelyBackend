package com.metrica.vibely.data.model.mapper;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;

/**
 * @since 2023-11-14
 * @author Raul
 * @version 1.0
 *
 */
public class UserMapper {

	public static User toEntity(UserDTO userdto) {
		User user = new User();
		
		user.setNickname(userdto.getNickname());
		user.setUsername(userdto.getUsername());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		
		return user;
	}
	
	public static UserDTO toDTO(User user) {
		UserDTO userdto = new UserDTO();
		
		userdto.setNickname(user.getNickname());
		userdto.setUsername(user.getUsername());
		userdto.setEmail(user.getEmail());
		userdto.setPassword(user.getPassword());
		
		return userdto;
	}
}
