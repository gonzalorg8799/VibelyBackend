package com.metrica.vibely.data.service.impl;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.repository.UserRepository;

/**
 * @since 2023-11-23
 * @author Raul
 * @version 1.0
 */
@Service
public class ChatServiceImpl implements ChatService {

	// ATRIBUTES
	UserRepository userRepository;
	
	// CONSTRUCTOR
	public ChatServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
