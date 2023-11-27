package com.metrica.vibely.data.service.impl;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.ChatService;

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

    @Override
    public ChatDTO getByMember(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatDTO create(ChatDTO ChatDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatDTO updateTitle(UUID chatId, String title) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatDTO addMembers(UUID chatId, Set<User> membersToAdd) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatDTO removeMembers(UUID chatId, Set<User> membersToRemove) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatDTO updateType(UUID chatId, ChatType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatDTO updateChatStatus(UUID chatId, ChatStatus status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<User> getMembers(UUID chatId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Message> getMessages(UUID chatId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(UUID chatId) {
        // TODO Auto-generated method stub
        
    }

	@Override
	public ChatDTO getById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatDTO update(ChatDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
