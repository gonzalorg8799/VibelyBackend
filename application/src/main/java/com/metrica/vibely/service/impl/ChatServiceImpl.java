package com.metrica.vibely.service.impl;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatState;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.model.mapper.ChatMapper;
import com.metrica.vibely.data.repository.ChatRepository;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.service.ChatService;

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
        chat.setStatus		(ChatStatus.ACTIVE);
        chat.setState 		(ChatState.ENABLED);
        chat.setTitle 		(ChatDto.getTitle());
        chat.setType		(ChatDto.getType());       
        return ChatMapper.toDTO(this.chatRepository.save(chat)); 
    }

    @Override
    public ChatDTO addMembers(UUID chatId, Set<UUID> membersToAdd) {
        Chat chat = this.chatRepository.findById(chatId).orElseThrow();
        
        Set<User> addedParts = chat.getParticipants();
        
        addedParts.addAll(membersToAdd.stream()
        							  .map(p -> this.userRepository.findById(p).orElseThrow())
        							  .collect(Collectors.toSet()));
        chat.setParticipants(addedParts);
        
        return ChatMapper.toDTO(this.chatRepository.save(chat));
    }

    @Override
    public ChatDTO removeMembers(UUID chatId, Set<UUID> membersToRemove) {
        Chat chat = this.chatRepository.findById(chatId).orElseThrow();
        
        Set<User> removedParts = chat.getParticipants();
        
        removedParts.removeAll(membersToRemove.stream()
        							  .map(p -> userRepository.findById(p).orElseThrow())
        							  .collect(Collectors.toSet()));
        chat.setParticipants(removedParts);
        
        return ChatMapper.toDTO(this.chatRepository.save(chat));
    }

    @Override
    public Set<User> getMembers(UUID chatId) {
        return this.chatRepository.findById(chatId).orElseThrow().getParticipants();
    }

    @Override
    public Set<Message> getMessages(UUID chatId) {
        return this.chatRepository.findById(chatId).orElseThrow().getMessages();
    }

    @Override
    public void deleteById(UUID chatId) {
        Chat chat = this.chatRepository.findById(chatId).orElseThrow();
        chat.setState(ChatState.DISABLED);
        this.chatRepository.save(chat);
    }

	@Override
		public ChatDTO getById(UUID id) {
			return ChatMapper.toDTO(this.chatRepository.findById(id).orElseThrow());
		}

	@Override
	public ChatDTO update(ChatDTO dto) {
		Chat chat = this.chatRepository.findById(dto.getChatId()).orElseThrow();
		
		String newTitle = dto.getTitle();
		ChatType newType = dto.getType();
		ChatStatus newStatus = dto.getStatus();
		
		if(newTitle  != null) { chat.setTitle(newTitle); }
		if(newType   != null) { chat.setType(newType); }
		if(newStatus != null) { chat.setStatus(newStatus); }
		
		return ChatMapper.toDTO(this.chatRepository.save(chat));
	}	
}
