package com.metrica.vibely.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.service.ChatService;
import com.metrica.vibely.model.request.CreateChatRequest;

import jakarta.validation.Valid;

/**
 * @since 2023-11-20
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
	
//	<<--FIELDS-->
	private ChatService chatService;
	
//	<<--CONSTRUCTOR-->>
	@Autowired
	public ChatController(ChatService chatService) {
		this.chatService=chatService;
	}
	
//	<<-METHODS->>
	@GetMapping("/{id}")
	public ChatDTO getById(@PathVariable UUID chatId) {
		return chatService.getById(chatId);
	}
	
//	@PutMapping("/{id}")
//	public ResponseEntity<ChatDTO> updateById(
//			@RequestBody @Valid 
//			CreateChatRequest chat, 
//			BindingResult bindingResult) {
//		
//	}
}
