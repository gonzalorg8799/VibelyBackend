package com.metrica.vibely.data.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.MessageStatus;
import com.metrica.vibely.data.util.Copyable;

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
public class Message implements Copyable<Message> {

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
            name = "chat_id",
            unique = true,
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_chat_message"))
    private Chat chat;
    @OneToOne(optional = false)
    @JoinColumn(
            name = "sender_id",
            unique = true,
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_chat_user"))
    private User sender;
    
    // <<-CONSTRUCTORS->>
    public Message(UUID uuid) {
    	this.messageId = uuid;
    }
    
    public Message() {
        this.setMessageId(null);
        this.setChat     (null);
        this.setSender   (null);
    } 

    public Message(
            UUID messageId,
            LocalDateTime creationTimestamp,
            MessageStatus status,
            String content,
            Chat chat,
            User sender) {
        this.setMessageId        (messageId);
        this.setCreationTimestamp(creationTimestamp);
        this.setStatus           (status);
        this.setContent          (content);
        this.setChat             (chat);
        this.setSender           (sender);
    }
    
    // <<-METHODS->>
    @Override
    public Message deepCopy() {
        Message copy = new Message();
        
        copy.setMessageId        (this.messageId);
        copy.setCreationTimestamp(this.creationTimestamp);
        copy.setStatus           (this.status);
        copy.setContent          (this.content);
        copy.setChat             (this.chat);
        copy.setSender           (this.sender);
        
        return copy;
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

    public Chat getChat() {
        return this.chat.deepCopy();
    }

    public void setChat(final Chat chat) {
        if (chat == null) {
            this.chat = new Chat();
        } else {
            this.chat = chat.deepCopy();
        }
    }

    public User getSender() {
        return this.sender.deepCopy();
    }

    public void setSender(final User sender) {
        if (sender == null) {
            this.sender = new User();
        } else {
            this.sender = sender.deepCopy();
        }
    }
    
}
