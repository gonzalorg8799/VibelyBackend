package com.metrica.vibely.data.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.MessageStatus;

/**
 * <h1>Message DTO</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Adri
 */
public class MessageDTO {

	
	// <<-FIELDS->>

    // Basic
    private UUID messageId;
    private LocalDateTime creationTimestamp;
    private MessageStatus status;
    private String content;

    // Relations
    private UUID chat;
    private UUID sender;
    
    // <<-CONSTRUCTORS->>
 // <<-CONSTRUCTORS->>
    public MessageDTO() {
        this.setMessageId(null);
        this.setChat     (null);
        this.setSender   (null);
    }

    public MessageDTO(
            UUID messageId,
            LocalDateTime creationTimestamp,
            MessageStatus status,
            String content,
            UUID chat,
            UUID sender) {
        this.setMessageId        (messageId);
        this.setCreationTimestamp(creationTimestamp);
        this.setStatus           (status);
        this.setContent          (content);
        this.setChat             (chat);
        this.setSender           (sender);
    }
    
    //<<-METHODS->>
    
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
        if (messageId == null) {
            this.messageId = UUID.randomUUID();
        } else {
            this.messageId = messageId;
        }
    }

    public LocalDateTime getCreationTimestamp() {
        return this.creationTimestamp;
    }

    public void setCreationTimestamp(final LocalDateTime creationTimestamp) {
        if (creationTimestamp == null) {
            this.creationTimestamp = LocalDateTime.now();
        } else {
            this.creationTimestamp = creationTimestamp;
        }
    }

    public MessageStatus getStatus() {
        return this.status;
    }

    public void setStatus(final MessageStatus status) {
        if (status == null) {
            this.status = MessageStatus.PENDING;
        } else {
            this.status = status;
        }
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
        if (chat == null) {
            this.chat = UUID.randomUUID();
        } else {
            this.chat = chat;
        }
    }

    public UUID getSender() {
        return this.sender;
    }

    public void setSender(final UUID sender) {
        if (sender == null) {
            this.sender = UUID.randomUUID();
        } else {
            this.sender = sender;
        }
    }
    
	
}
