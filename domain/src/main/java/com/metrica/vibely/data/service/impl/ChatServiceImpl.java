package com.metrica.vibely.data.service.impl;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.model.mapper.ChatMapper;
import com.metrica.vibely.data.repository.ChatRepository;
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
	ChatRepository chatRepository;
	
	// CONSTRUCTOR
	public ChatServiceImpl(UserRepository userRepository, ChatRepository chatRepository) {
		this.userRepository = userRepository;
		this.chatRepository = chatRepository;
	}

    @Override
    public ChatDTO create(ChatDTO ChatDto) {
    	Set<User> participants = ChatDto.getParticipants().stream()
    													  .map(p -> userRepository.findById(p).get())
    													  .collect(Collectors.toSet());
    	
        Chat chat = ChatMapper.toEntity(ChatDto, participants, null);
        
        chat.setCreationDate(LocalDateTime.now());
        chat.setLastActivity(null);
        chat.setStatus(ChatStatus.ACTIVE);
        chat.setTitle(ChatDto.getTitle());
        if(participants.size() > 2) { chat.setType(ChatType.DIRECT_MESSAGE); } 
        else if(participants.size() > 1 && participants.size() == 2) { chat.setType(ChatType.DIRECT_MESSAGE); }
        
        return ChatMapper.toDTO(chatRepository.save(chat)); 
    }

    @Override
    public ChatDTO addMembers(UUID chatId, Set<User> membersToAdd) {
        Chat chat = chatRepository.findById(chatId).orElseThrow();
        
        chat.setParticipants(membersToAdd.stream()
        								 .filter(p -> !chat.getParticipants().contains(p))
        								 .collect(Collectors.toSet()));
        
        return ChatMapper.toDTO(chatRepository.save(chat));
    }

    @Override
    public ChatDTO removeMembers(UUID chatId, Set<User> membersToRemove) {
        Chat chat = chatRepository.findById(chatId).orElseThrow();
        
        chat.getParticipants().removeAll(membersToRemove.stream()
        											 .filter(p -> chat.getParticipants().contains(p))
        											 .collect(Collectors.toSet()));
        
        return ChatMapper.toDTO(chatRepository.save(chat));
    }

    @Override
    public Set<User> getMembers(UUID chatId) {
        return chatRepository.findById(chatId).orElseThrow().getParticipants();
    }

    @Override
    public Set<Message> getMessages(UUID chatId) {
        return chatRepository.findById(chatId).orElseThrow().getMessages();
    }

    @Override
    public void deleteById(UUID chatId) {
        chatRepository.deleteById(chatId);
    }

	@Override
		public ChatDTO getById(UUID id) {
			return ChatMapper.toDTO(chatRepository.findById(id).orElseThrow());
		}

	@Override
	public ChatDTO update(ChatDTO dto) {
		dto = updateTitle(dto.getChatId(), dto.getTitle());
		dto = updateType(dto.getChatId(), dto.getType());
		dto = updateChatStatus(dto.getChatId(), dto.getStatus());
		
		return dto;
	}
	
    private ChatDTO updateTitle(UUID chatId, String title) {
    	Chat chat = chatRepository.findById(chatId).get();
    	if(title != null) { chat.setTitle(title); }
        return ChatMapper.toDTO(chatRepository.save(chat));
    }
    private ChatDTO updateType(UUID chatId, ChatType type) {
        Chat chat = chatRepository.findById(chatId).orElseThrow();
        chat.setType(type);
    	return ChatMapper.toDTO(chatRepository.save(chat)); 
    }

    private ChatDTO updateChatStatus(UUID chatId, ChatStatus status) {
    	Chat chat = chatRepository.findById(chatId).orElseThrow();
        chat.setStatus(status);
    	return ChatMapper.toDTO(chatRepository.save(chat));
    }
}
