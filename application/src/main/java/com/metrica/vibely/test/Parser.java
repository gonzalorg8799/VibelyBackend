package com.metrica.vibely.test;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.UserDTO;

public class Parser {
	public UserDTO toUserDTO2(User user) {
        UserDTO userDto = new UserDTO();

        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setNickname(user.getNickname());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
    public static User toEntity(UserDTO userDto) {
    	User user = new User();
    	user.setUsername(userDto.getUsername());
    	user.setPassword(userDto.getPassword());
    	user.setNickname(userDto.getNickname());
    	user.setEmail(userDto.getEmail());
    	return user;
    }
}
