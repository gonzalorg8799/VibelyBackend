package com.metrica.vibely.controller;

import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatState;
import com.metrica.vibely.data.service.ChatService;
import com.metrica.vibely.model.request.AddRemoveChatRequest;
import com.metrica.vibely.model.request.CreateChatRequest;
import com.metrica.vibely.model.request.UpdateChatRequest;
import com.metrica.vibely.model.response.create.CreateChatResponse;
import com.metrica.vibely.model.response.update.UpdateChatResponse;

import jakarta.validation.Valid;

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

/**
 * <h1>Chat Controller</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Raul
 */
@RestController
@RequestMapping("/api/v1/chats")
public class ChatController {

    // <<-FIELDS->>
    private ChatService chatService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // <<-METHODS->>
    @GetMapping("/{id}")
    public ResponseEntity<ChatDTO> getById(@PathVariable UUID id) {
    	ChatDTO chatDto = chatService.getById(id); 
    	
    	if (chatDto.getState()   != ChatState.DISABLED) {
                return ResponseEntity.ok().body(chatDto);
    	}
    	return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<CreateChatResponse> create(
            @RequestBody
//          @Valid
            CreateChatRequest chatRequest,
            BindingResult bindingResult
     ) {
         if (bindingResult.hasErrors()) {
             return ResponseEntity.badRequest().build();
         }
         ChatDTO chatDto = this.chatService.create(chatRequest.toChatDTO());
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(new CreateChatResponse().generateResponse(chatDto));
     }
	
	@PutMapping("/{id}")
    public ResponseEntity<UpdateChatResponse> updateById(
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
        return ResponseEntity.ok().body(new UpdateChatResponse().generateResponse(updatedDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable UUID id) {
        this.chatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
	
	@PutMapping("/add/{id}")
	public ResponseEntity<CreateChatResponse> addMember(
			@PathVariable
			UUID id,
			@RequestBody
			AddRemoveChatRequest chatRequest,
			BindingResult bindingResult
	 ) {
         if (!bindingResult.hasErrors()) {
             ChatDTO chatDto = chatRequest.toDTO();
             chatDto.setChatId(id);

             ChatDTO updatedDto = this.chatService.addMembers(id, chatDto.getParticipants());

             return ResponseEntity.ok().body(new CreateChatResponse().generateResponse(updatedDto));
         }
         return ResponseEntity.badRequest().build();
     }
	
	@PutMapping("/remove/{id}")
	public ResponseEntity<CreateChatResponse> removeMember(
			@PathVariable
			UUID id,
			@RequestBody
			AddRemoveChatRequest chatRequest,
			BindingResult bindingResult
	) {
        if (!bindingResult.hasErrors()) {
            ChatDTO chatDto = chatRequest.toDTO();
            chatDto.setChatId(id);

            ChatDTO updatedDto = this.chatService.removeMembers(id, chatDto.getParticipants());
            return ResponseEntity.ok().body(new CreateChatResponse().generateResponse(updatedDto));
        }
        return ResponseEntity.badRequest().build();
    }

}