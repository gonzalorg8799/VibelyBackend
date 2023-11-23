package com.metrica.vibely.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.service.ChatService;

/**
 * @since 2023-11-20
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class ChatController {
	
//	<<--FIELDS-->
	private ChatService chatService;
	
//	<<--CONSTRUCTOR-->>
	@Autowired
	public ChatController(ChatService chatService) {
		this.chatService=chatService;
	}
}
