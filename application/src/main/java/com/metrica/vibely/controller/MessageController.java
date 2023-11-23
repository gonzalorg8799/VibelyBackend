package com.metrica.vibely.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.service.MessageService;

/**
 * @since 2023-11-20
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class MessageController {
//	<<--FIELDS-->>
	private MessageService messageService;
//	<<--CONSTRUCTOR-->>
//	public MessageController(MessageService messageService) {
//		this.messageService=messageService;
//	}
//	<<--METHODS-->>

}
