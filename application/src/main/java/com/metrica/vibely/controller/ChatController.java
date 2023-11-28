package com.metrica.vibely.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.service.ChatService;
import com.metrica.vibely.model.request.CreateChatRequest;
import com.metrica.vibely.model.response.CreateChatResponse;
import com.metrica.vibely.model.response.UpdateChatRequest;

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
	public ChatDTO getById(@PathVariable UUID id) {
		return chatService.getById(id);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<CreateChatResponse> updateById(
            @PathVariable
            UUID id,
            @RequestBody
            @Valid
            UpdateChatRequest chatRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        
        ChatDTO chatDTO = chatRequest.toDTO();
        chatDTO.setChatId(id);
        
        ChatDTO updatedDTO = this.chatService.update(chatDTO);
        return ResponseEntity.ok()
                .body(new CreateChatResponse().generateResponse(updatedDTO));
    }

	@PostMapping("/newchat")
	public ResponseEntity<CreateChatResponse> create(
			@RequestBody
//			@Valid
			CreateChatRequest chatRequest,
			BindingResult bindingResult
		) {
		if(bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		ChatDTO chatDto = this.chatService.create(chatRequest.toChatDTO());
		return ResponseEntity.ok()
							.body(new CreateChatResponse().generateResponse(chatDto));
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByUsername(@PathVariable UUID id) {
        this.chatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
	
}
