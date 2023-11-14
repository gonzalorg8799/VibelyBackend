package com.metrica.vibely.model.mapper;

import com.metrica.vibely.model.request.CreateUserRequest;

public class CreateUserMapper {
	static UserDTO toUserDto(CreateUserRequest request) {
		UserDTO userDto = new UserDTO();
		userDto.setUsername(request.getUsername());
		userDto.setPassword(request.getPassword());
		userDto.setNickname(request.getNickname());
		userDto.setNickname(request.getEmail());
	}
	static CreateUserRequest toUserRequest(UserDto userDto) {
		CreateUserRequest userRequest = new CreateUserRequest();
		userRequest.setUsername(userDto.getUsername());
		userRequest.setPassword(userDto.getPassword());
		userRequest.setNickname(userDto.getNickname());
		userRequest.setEmail(userDto.getEmail());
	}
}
