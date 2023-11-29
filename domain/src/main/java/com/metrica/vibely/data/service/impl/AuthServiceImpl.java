package com.metrica.vibely.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.exception.InvalidCredentialsException;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.AuthService;
import com.metrica.vibely.data.util.ApiKeyManager;
import com.metrica.vibely.data.util.PasswordHasher;

/**
 * 
 * @since 2023-11-14
 * @version 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

	// <<-FIELD->>
	private UserRepository userRepository;

	// <<-CONSTRUCTOR->>
	@Autowired
	public AuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// <<-METHODS->>
	@Override
	public String authenticate(final String username, final String password) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new InvalidCredentialsException());
		if (PasswordHasher.matches(password, user.getPassword())) {
			user.setLogins(user.getLogins() + 1);
			userRepository.save(user);
			return ApiKeyManager.generate();
		} 
		else throw new InvalidCredentialsException();

	}

}
