package com.metrica.vibely.model.mapper;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.model.request.CreateUserRequest;

/**
 * 
 * 
 * @since 2023-11-14
 * @author 
 * @version 1.0
 */
public class CreateUserMapper {

    // <<-METHODS->>
    public static UserDTO toUserDTO(CreateUserRequest request) {
        UserDTO userDto = new UserDTO();

        userDto.setUsername(request.getUsername());
        userDto.setPassword(request.getPassword());
        userDto.setNickname(request.getNickname());
        userDto.setEmail(request.getEmail());

        return userDto;
    }

    public static CreateUserRequest toUserRequest(UserDTO userDto) {
        CreateUserRequest userRequest = new CreateUserRequest();

        userRequest.setUsername(userDto.getUsername());
        userRequest.setPassword(userDto.getPassword());
        userRequest.setNickname(userDto.getNickname());
        userRequest.setEmail(userDto.getEmail());

        return userRequest;
    }
    
}
