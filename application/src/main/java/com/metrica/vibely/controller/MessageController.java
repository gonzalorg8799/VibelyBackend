package com.metrica.vibely.controller;

import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.service.MessageService;
import com.metrica.vibely.model.request.CreateMessageRequest;

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
    private MessageService messageService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // <<-METHODS->>
    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.messageService.getById(id));
    }
    
	@PostMapping("/create")
	public ResponseEntity<MessageDTO> create(
            @RequestBody 
            CreateMessageRequest createMessage,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();

        MessageDTO messageDto = createMessage.toDto();
        MessageDTO message = this.messageService.create(messageDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
	
    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(
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

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}