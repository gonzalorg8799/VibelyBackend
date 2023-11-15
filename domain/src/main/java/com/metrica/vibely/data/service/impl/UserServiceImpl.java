package com.metrica.vibely.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.Status;
import com.metrica.vibely.data.model.mapper.UserMapper;
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
	public UserServiceImpl(UserRepository userRepostory) {
		this.userRepository = userRepostory;
	}
	
	@Override
	public UserDTO getByUsername(String username) {
		return UserMapper.toDTO(userRepository.findByUsername(username).orElseThrow());
	}

	@Override
	public void deleteByUsername(String username) {
	    userRepository.deleteByUsername(username);
	}

	@Override
	public UserDTO create(UserDTO userParam) {
		User user = UserMapper.toEntity(userParam);
		
		user.setStatus(Status.ENABLED);
		user.setLogins(1);
		user.setFollowers(null);
        user.setFollowing(null);
        user.setChats(null);
        user.setPassword(userParam.getPassword());
		
        userRepository.save(user);
        
		return UserMapper.toDTO(user);
	}

	@Override
	public UserDTO update(UserDTO updatedUser) {
	    // no permite cambiar el nombre de usuario 
		String username = updatedUser.getUsername();
		User user = userRepository.findByUsername(username).orElseThrow();
		
		String password = updatedUser.getPassword();
		String nickname = updatedUser.getNickname();
		String email = updatedUser.getEmail();

		if(!username.equals(user.getUsername())) { user.setUsername(username); } 
		if(!password.equals(user.getPassword())) { user.setPassword(password); }
		if(!nickname.equals(user.getNickname())) { user.setNickname(nickname); }
		if(!email.equals(user.getEmail())) { user.setEmail(email); }
		
		userRepository.save(user);
		
		return UserMapper.toDTO(user);
	}
	
}
