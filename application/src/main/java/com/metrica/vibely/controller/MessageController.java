package com.metrica.vibely.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.service.MessageService;
import com.metrica.vibely.model.request.CreateMessageRequest;

import jakarta.validation.Valid;

/**
 * @since 2023-11-20
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
//	<<--FIELDS-->>
	private MessageService messageService;
//	<<--CONSTRUCTOR-->>
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService=messageService;
	}
//	<<--METHODS-->>
	@GetMapping("/{id}")
	public MessageDTO getById(@PathVariable UUID id) {
		return this.messageService.getById(id);
	}
	@PostMapping("/create")
	public ResponseEntity<MessageDTO> create(
			@RequestBody 
			@Valid 
			CreateMessageRequest createMessage,
			BindingResult bindingResult){
		
		if(bindingResult.hasErrors())return ResponseEntity.badRequest().build();
	
		MessageDTO messageDto = CreateMessageRequest.toMessageDTO(createMessage);
		MessageDTO message = this.messageService.create(messageDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
	@PutMapping("/{id}")
	public ResponseEntity<MessageDTO> update(
			@RequestBody 
			@PathVariable 
			CreateMessageRequest createMessage, 
			BindingResult bindingResult){
		
		if(bindingResult.hasErrors())return ResponseEntity.badRequest().build();
		
		MessageDTO messageDto = CreateMessageRequest.toMessageDTO(createMessage);
		MessageDTO message = this.messageService.update(messageDto);
		
		return ResponseEntity.ok(message);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id){
		
		this.messageService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
