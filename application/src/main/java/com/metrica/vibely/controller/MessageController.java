package com.metrica.vibely.controller;

import com.metrica.vibely.controller.util.ResponseManager;
import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.enumerator.MessageState;
import com.metrica.vibely.model.request.CreateMessageRequest;
import com.metrica.vibely.model.response.create.CreateMessageResponse;
import com.metrica.vibely.model.response.get.GetMessageResponse;
import com.metrica.vibely.model.response.update.UpdateMessageResponse;
import com.metrica.vibely.service.MessageService;

import java.util.NoSuchElementException;
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
 * <h1>Message Controller</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Gonzalo
 */
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    // <<-FIELDS->>
	private ResponseManager responseManager;
    private MessageService messageService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public MessageController(MessageService messageService, ResponseManager responseManager) {
    	this.responseManager = responseManager;
        this.messageService = messageService;
    }

    // <<-METHODS->>
    @GetMapping("/{id}")
    public ResponseEntity<GetMessageResponse> getById(@PathVariable UUID id) {
    	MessageDTO messageDto = this.messageService.getById(id);
    	if(messageDto.getState()== MessageState.DISABLED) throw new NoSuchElementException();
    	return this.responseManager.generateGetResponse(messageDto);
    }
    
	@PostMapping("/create")
	public ResponseEntity<CreateMessageResponse> create(
            @RequestBody 
            CreateMessageRequest createMessage,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();

        MessageDTO messageDto = createMessage.toDto();
        MessageDTO message = this.messageService.create(messageDto);

        return this.responseManager.generateCreateResponse(message);
    }
	
    @PutMapping("/{id}")
    public ResponseEntity<UpdateMessageResponse> update(
            @PathVariable
            UUID id,
            @RequestBody
            CreateMessageRequest createMessage,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();

        MessageDTO messageDto = createMessage.toDto();
        
        messageDto.setMessageId(id);
        MessageDTO message = this.messageService.update(messageDto);

        return this.responseManager.generateUpdateResponse(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}