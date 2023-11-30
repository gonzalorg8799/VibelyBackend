package com.metrica.vibely.controller;

import com.metrica.vibely.controller.util.ResponseManager;
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatState;
import com.metrica.vibely.data.service.ChatService;
import com.metrica.vibely.model.request.AddRemoveChatRequest;
import com.metrica.vibely.model.request.CreateChatRequest;
import com.metrica.vibely.model.request.UpdateChatRequest;
import com.metrica.vibely.model.response.create.CreateChatResponse;
import com.metrica.vibely.model.response.get.GetChatResponse;
import com.metrica.vibely.model.response.update.UpdateChatResponse;

import jakarta.validation.Valid;

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
	ResponseManager responseManager;
    private ChatService chatService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public ChatController(ChatService chatService, ResponseManager responseManager) {
    	this.responseManager = responseManager;
        this.chatService = chatService;
    }

    // <<-METHODS->>
    @GetMapping("/{id}")
    public ResponseEntity<GetChatResponse> getById(@PathVariable UUID id) {
    	ChatDTO chatDto = chatService.getById(id); 
    	
    	if (chatDto.getState()   != ChatState.DISABLED) {
    		return this.responseManager.generateGetResponse(chatDto);
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
         return this.responseManager.generateCreateResponse(chatDto);
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
        return this.responseManager.generateUpdateResponse(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable UUID id) {
        this.chatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
	
	@PutMapping("/add/{id}")
	public ResponseEntity<UpdateChatResponse> addMember(
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

             return this.responseManager.generateUpdateResponse(updatedDto);
         }
         return ResponseEntity.badRequest().build();
     }
	
	@PutMapping("/remove/{id}")
	public ResponseEntity<UpdateChatResponse> removeMember(
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
            return this.responseManager.generateUpdateResponse(updatedDto);
        }
        return ResponseEntity.badRequest().build();
    }

}