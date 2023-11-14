package com.metrica.vibely.data.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Message {
    
    // <<-FIELDS->>	
	
	//Basic
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "messageId", nullable = false)
	private UUID messageId;
    private String srcUsername, content;
    
    //Relations
	@JoinColumn(name = "chat_id", nullable = false, foreignKey = @ForeignKey(name = "fk_chat_message"))
    private Chat chatOwner;

 
    
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
