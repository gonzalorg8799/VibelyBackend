package com.metrica.vibely.data.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.MessageStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 * <h1>Message Entity</h1>
 * 
 * @since 2023-11-13
 * @version 1.0
 * @author Adrian, Alex
 */
@Entity
public class Message {
    
    // <<-FIELDS->>	
	
	// Basic
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "message_id")
	private UUID messageId;
	@Column(name = "creation_timestamp")
	private LocalDateTime creationTimestamp;
	private MessageStatus status;
    private String content;
    
    // Relations
    @OneToOne(optional = false)
    @JoinColumn(
            name = "sender_id",
            unique = true,
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_chat_user"))
    private User sender;
    @OneToOne(optional = false)
	@JoinColumn(
	        name = "chat_id",
	        unique = true,
	        nullable = false,
	        foreignKey = @ForeignKey(name = "fk_chat_message"))
    private Chat chat;
    
    // <<-CONSTRUCTORS->>
    public Message() {
        this.setMessageId(null);
    }

    public Message(
            UUID messageId,
            LocalDateTime creationTimestamp,
            MessageStatus status,
            String content,
            User sender,
            Chat chat) {
        this.setMessageId(messageId);
        this.setCreationTimestamp(creationTimestamp);
        this.setStatus(status);
        this.setContent(content);
        this.setSender(sender);
        this.setChat(chat);
    }
    
    /**
     * Constructs a copy of the given entity.
     * 
     * @param message the message to copy
     */
    public Message(Message message) {
        this.setMessageId        (message.getMessageId());
        this.setCreationTimestamp(message.getCreationTimestamp());
        this.setStatus           (message.getStatus());
        this.setContent          (message.getContent());
        this.setSender           (message.getSender());
        this.setChat             (message.getChat());
    }

    // <<-METHODS->>
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
        Message other = (Message) obj;
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

    public User getSender() {
        return new User(this.sender);
    }

    public void setSender(final User sender) {
        this.sender = new User(sender);
    }

    public Chat getChat() {
        return new Chat(this.chat);
    }

    public void setChat(final Chat chat) {
        this.chat = new Chat(chat);
    }
    
}
