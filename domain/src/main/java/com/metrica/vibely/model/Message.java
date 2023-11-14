package com.metrica.vibely.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//revisar si no tiene id explota
public class Message {
    
    // <<-FIELDS->>
	//habría que poner el generated value para el id de cada mensaje
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID messageId;
    private Chat chatOwner;
    private String srcUsername;
    private String content;
    
    // <<-CONSTRUCTORS->>
    public Message() {
    }
    
    public Message(
            Chat chatOwner,
            String srcUsername,
            String content) {
        this.setChatOwnerId(chatOwner);
        this.setSrcUsername(srcUsername);
        this.setContent(content);
    }

    // <<-GETTERS & SETTERS->>
    public Chat getChatOwner() {
        return chatOwner;
    }

    public void setChatOwnerId(Chat chatOwner) {
        this.chatOwner = chatOwner;
    }

    public String getSrcUsername() {
        return srcUsername;
    }

    public void setSrcUsername(String srcUsername) {
        this.srcUsername = srcUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
