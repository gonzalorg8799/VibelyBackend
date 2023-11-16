package com.metrica.vibely.data.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * <h1>Chat Entity</h1>
 * 
 * @since 2023-11-13
 * @version 1.0
 * @author Adrian, Alex
 */

@Entity
public class Chat {
    
	// <<-FIELDS->>
	
	// Basic
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID chatId;
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	@Enumerated(value = EnumType.STRING)
    private ChatType type;
    private ChatStatus status;
	private String title;
    @Column(name = "last_activity")
	private LocalDateTime lastActivity;
	
	// Relations
	@OneToMany(mappedBy = "userId")
    private Set<User> participants;
	@OneToMany(mappedBy = "messageId")
    private Set<Message> messages;
    
    // <<-CONSTRUCTORS->>
    public Chat() {
        this.setChatId(null);
    }

    public Chat(
            UUID chatId,
            LocalDateTime lastActivity,
            ChatType type,
            ChatStatus status,
            String title,
            LocalDateTime creationDate,
            Set<User> participants,
            Set<Message> messages
    ) {
        this.setChatId(chatId);
        this.setLastActivity(lastActivity);
        this.setType(type);
        this.setStatus(status);
        this.setTitle(title);
        this.setCreationDate(creationDate);
        this.setParticipants(participants);
        this.setMessages(messages);
    }
    
    // <<-METHODS->>
    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Chat other = (Chat) obj;
        return Objects.equals(this.chatId, other.chatId);
    }

    // <<-GETTERS & SETTERS->>
    public UUID getChatId() {
        return chatId;
    }

    public void setChatId(UUID chatId) {
        if (chatId == null) {
            this.chatId = UUID.randomUUID();
        } else {
            this.chatId = chatId;
        }
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        if (creationDate == null) {
            this.creationDate = LocalDateTime.now();
        } else {
            this.creationDate = creationDate;
        }
    }

    public ChatType getType() {
        return type;
    }

    public void setType(ChatType type) {
        if (type == null) {
            this.type = ChatType.DIRECT_MESSAGE;
        } else {
            this.type = type;
        }
    }

    public ChatStatus getStatus() {
        return status;
    }

    public void setStatus(ChatStatus status) {
        if (status == null) {
            this.status = ChatStatus.ACTIVE;
        } else {
            this.status = status;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        if (lastActivity == null) {
            this.lastActivity = LocalDateTime.now();
        } else {
            this.lastActivity = lastActivity;
        }
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
    
}
