package com.metrica.vibely.data.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.enumerator.MessageState;
import com.metrica.vibely.data.model.enumerator.MessageStatus;
import com.metrica.vibely.data.model.mapper.MessageMapper;
import com.metrica.vibely.data.repository.ChatRepository;
import com.metrica.vibely.data.repository.MessageRepository;
import com.metrica.vibely.data.repository.UserRepository;
import com.metrica.vibely.data.service.MessageService;

/**
 * @since 2023-11-27
 * @version 1.0
 * @author Gonzalo
 */
@Service
public class MessageServiceImpl implements MessageService{
	private MessageRepository messageRepository;
	private ChatRepository chatRepository;
	private UserRepository userRepository;
	
	@Autowired
	public MessageServiceImpl(final MessageRepository messageRepository, final ChatRepository chatRepository, final UserRepository userRepository) {
		this.messageRepository=messageRepository;
		this.chatRepository=chatRepository;
		this.userRepository=userRepository;
	}
	
	@Override
	public MessageDTO getById(UUID id) {
		return MessageMapper.toDTO(this.messageRepository.findById(id).orElseThrow());
	}

	@Override
	public MessageDTO create(final MessageDTO dto) {
		Message message = MessageMapper.toEntity(dto, null, null);
		Chat chat = this.chatRepository.findById(dto.getChat()).orElseThrow();
		User user = this.userRepository.findById(dto.getSender()).orElseThrow();
		
		message.setCreationTimestamp(LocalDateTime.now());
		message.setStatus			(MessageStatus.PENDING);
		message.setState			(MessageState.ENABLED);
		message.setContent			(message.getContent());
		message.setSender			(user);
		message.setChat				(chat);
		
		return MessageMapper.toDTO(messageRepository.save(message));
	}

	@Override
	public MessageDTO update(MessageDTO dto) {
	    Message message 	 = this.messageRepository.findById(dto.getMessageId()).orElseThrow();
		
	    MessageStatus status = message.getStatus();
		MessageState state	 = message.getState();
		String content       = message.getContent();
		
		if(status !=null)     message.setStatus(status);
		if(state  !=null)	  message.setState(state);
		if(content!=null)     message.setContent(content);
		
		return MessageMapper.toDTO(this.messageRepository.save(message));
	}
	
	@Override
	public void deleteById(UUID id) {
		messageRepository.deleteById(id);
		
	}

	@Override
	public MessageDTO getByContent(String content) {
		return null;
	}

	@Override
	public UUID getSender(UUID messageId) {
		MessageDTO messageDto = MessageMapper.toDTO(messageRepository.findById(messageId).get());
		return messageDto.getSender();
	}

	@Override
	public UUID getChat(UUID messageId) {
		MessageDTO messageDto = MessageMapper.toDTO(messageRepository.findById(messageId).get());
		return messageDto.getChat();
	}

}
