package com.metrica.vibely.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.helper.PasswordHashing;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepostory) {
		this.userRepository = userRepostory;
	}
	
	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow();
	}

	@Override
	public void deleteByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow();
		userRepository.delete(user);	
	}

	@Override
	public User create(User user) {
		try {
			user.setPassword(PasswordHashing.hash(user.getPassword()));
		} catch (Exception e) {
			System.err.println("Error en el algoritmo de hasheo");
		} 
		return userRepository.save(user);
	}

	@Override
	public User update(User updatedUser) {
		String username = updatedUser.getUsername();
		User user = userRepository.findByUsername(username).orElseThrow();
		
		try {
			String password;
			password = PasswordHashing.hash(updatedUser.getPassword());
			
			if(!password.equals(user.getPassword())) { 
				user.setPassword(password); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Error en el algoritmo de hasheo");
		} 
		
		String nickname = updatedUser.getNickname();
		String email = updatedUser.getEmail();
		
		if(!email.equals(user.getEmail())) { 
			user.setEmail(email); 
		}
		if(!nickname.equals(user.getNickname())) {
			user.setNickname(nickname);
		}
		if(!username.equals(user.getUsername())) {
			user.setUsername(username);
		} 
		return userRepository.save(user);
	}
	
}
