package com.metrica.vibely.model;

import java.util.List;
import java.util.UUID;

import com.metrica.vibely.model.enumerator.ChatType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Chat {
	// <<-FIELDS->>
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID chatId;
	
	@Enumerated(value = EnumType.STRING)
    private ChatType type;
	
    private List<User> participants;
    private List<Message> messages;
    
    // <<-CONSTRUCTORS->>
    public Chat() {
    }
    
    public Chat(
            UUID chatId,
            ChatType type,
            List<User> participants,
            List<Message> messages) {
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

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
    
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
