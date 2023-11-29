package com.metrica.vibely.data.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.MessageState;
import com.metrica.vibely.data.model.enumerator.MessageStatus;

/**
 * <h1>Message DTO</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Adri, Gonzalo
 */
public class MessageDTO {

	
	// <<-FIELDS->>

    // Basic
    private UUID messageId;
    private LocalDateTime creationTimestamp;
    private MessageStatus status;
    private MessageState state;
    private String content;

    // Relations
    private UUID chat;
    private UUID sender;
    
    // <<-CONSTRUCTORS->>
    public MessageDTO() {
    }

    public MessageDTO(
            UUID messageId,
            LocalDateTime creationTimestamp,
            MessageStatus status,
            MessageState state,
            String content,
            UUID chat,
            UUID sender) {
        this.setMessageId        (messageId);
        this.setCreationTimestamp(creationTimestamp);
        this.setStatus           (status);
        this.setState			 (state);
        this.setContent          (content);
        this.setChat             (chat);
        this.setSender           (sender);
    }
    
    //<<-METHODS->>
    
    public MessageState getState() {
		return state;
	}

	public void setState(MessageState state) {
		this.state = state;
	}

	@Override
    public int hashCode() {
        return Objects.hash(this.messageId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        MessageDTO other = (MessageDTO) obj;
        return Objects.equals(this.messageId, other.messageId);
    }
    
    // <<-GETTERS & SETTERS->>
    public UUID getMessageId() {
        return this.messageId;
    }

    public void setMessageId(final UUID messageId) {
        this.messageId = messageId;
    }

    public LocalDateTime getCreationTimestamp() {
        return this.creationTimestamp;
    }

    public void setCreationTimestamp(final LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public MessageStatus getStatus() {
        return this.status;
    }

    public void setStatus(final MessageStatus status) {
        this.status = status;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public UUID getChat() {
        return this.chat;
    }

    public void setChat(final UUID chat) {
        this.chat = chat;
    }

    public UUID getSender() {
        return this.sender;
    }

    public void setSender(final UUID sender) {
        this.sender = sender;
    }
    
	
}
