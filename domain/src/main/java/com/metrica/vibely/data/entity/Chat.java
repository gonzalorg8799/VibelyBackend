package com.metrica.vibely.data.entity;

import java.util.Set;
import java.util.UUID;

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
 * 
 * @since 2023-11-13
 * @version 1.0
 */

@Entity
public class Chat {
	// <<-FIELDS->>
	
	// Basic
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable = false)
    private UUID chatId;
	
	@Enumerated(value = EnumType.STRING)
    private ChatType type;
	
	// Relations
	@OneToMany(mappedBy = "participant_id")
    private Set<User> participants;
	
	@OneToMany(mappedBy = "message_id")
    private Set<Message> messages;
    
    // <<-CONSTRUCTORS->>
    public Chat() {
    }
    
    public Chat(
            UUID chatId,
            ChatType type,
            Set<User> participants,
            Set<Message> messages) {
        this.setChatId(chatId);
        this.setType(type);
        this.setParticipants(participants);
        this.setMessages(messages);
    }

    // <<-GETTERS & SETTERS->>
    public UUID getChatId() {
        return chatId;
    }

    public void setChatId(UUID chatId) {
        this.chatId = chatId;
    }

    public ChatType getType() {
        return type;
    }

    public void setType(ChatType type) {
        this.type = type;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    // add 1 participant
    
    public void setParticipants(Set<User> participants) {
        if (participants == null) participants = new java.util.HashSet<>();
        else this.participants = participants;
    }
    
    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
