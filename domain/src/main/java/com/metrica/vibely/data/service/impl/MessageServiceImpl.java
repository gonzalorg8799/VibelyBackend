package com.metrica.vibely.data.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.enumerator.MessageStatus;
import com.metrica.vibely.data.model.mapper.MessageMapper;
import com.metrica.vibely.data.repository.MessageRepository;
import com.metrica.vibely.data.service.MessageService;

/**
 * @since 2023-11-27
 * @version 1.0
 */
@Service
public class MessageServiceImpl implements MessageService{
	private MessageRepository messageRepository;
	
	@Autowired
	public MessageServiceImpl(final MessageRepository messageRepository) {
		this.messageRepository=messageRepository;
	}
	
	@Override
	public MessageDTO getById(UUID id) {
		return MessageMapper.toDTO(this.messageRepository.findById(id).orElseThrow());
	}

	@Override
	public MessageDTO create(final MessageDTO dto) {
		Message message = MessageMapper.toEntity(dto, null, null);
		
		message.setCreationTimestamp(LocalDateTime.now());
		message.setStatus			(MessageStatus.PENDING);
		message.setContent			(null);
		message.setSender			(null);
		message.setChat				(null);
		
		return MessageMapper.toDTO(messageRepository.save(message));
	}

	@Override
	public MessageDTO update(MessageDTO dto) {
		MessageDTO messageDto = MessageMapper.toDTO(messageRepository.findById(dto.getMessageId()).get());
		Message message = messageRepository.findById(dto.getMessageId()).orElseThrow();
		
		updateContent(dto.getMessageId(), dto.getContent());
		updateStatus (dto.getMessageId(), dto.getStatus());
		return MessageMapper.toDTO(messageRepository.save(MessageMapper.toEntity(messageDto, message.getChat(), message.getSender())));
	}
	
	private MessageDTO updateContent(final UUID id,final String content ) {
		Message message = messageRepository.findById(id).orElseThrow();
		
		if(content != null && !content.equals(message.getContent())) message.setContent(content);
		
		return MessageMapper.toDTO(message);
	}
	
	private MessageDTO updateStatus(final UUID id, final MessageStatus status) {
		Message message = messageRepository.findById(id).orElseThrow();
		
		if(status!=null&&!status.equals(message.getStatus())) message.setStatus(status);
		
		return MessageMapper.toDTO(message);
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
