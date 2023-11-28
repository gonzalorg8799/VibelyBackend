package com.metrica.vibely.data.model.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

/**
 * <h1>Chat DTO</h1>
 * 
 * @since 2023-11-20
 * @version 1.0 
 * @author Adri
 *
 */
public class ChatDTO {
	
	// <<-FIELDS->>

    // Basic
    private UUID chatId;
    private LocalDateTime creationDate;
    private ChatType type;
    private ChatStatus status;
    private String title;
    private LocalDateTime lastActivity;

    // Relations
    private Set<UUID> participants;
    private Set<UUID> messages;
    
    
    // <<-CONSTRUCTORS->>
    public ChatDTO() {
    }
    
    public ChatDTO(
    		UUID chatId,
    		LocalDateTime creationDate,
    		ChatType type, 
    		ChatStatus status, 
    		String title,
			LocalDateTime lastActivity, 
			Set<UUID> participants,
			Set<UUID> messages) 
    {
		this.chatId = chatId;
		this.creationDate = creationDate;
		this.type = type;
		this.status = status;
		this.title = title;
		this.lastActivity = lastActivity;
		this.participants = participants;
		this.messages = messages;
	}


 // <<-METHODS->>
    /**
     * Add a new participant to the chat.
     * 
     * @param participant the new user
     */
    public boolean addParticipant(final UUID participant) {
         return this.participants.add(participant);       
    }
    
    /**
     * Remove a participant from the chat.
     * 
     * @param participant the user to remove
     */
    public boolean removeParticipant(final UUID participant) {
         return this.participants.remove(participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.chatId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        ChatDTO other = (ChatDTO) obj;
        return Objects.equals(this.chatId, other.chatId);
    }

 // <<-GETTERS & SETTERS->>
    public UUID getChatId() {
        return this.chatId;
    }

    public void setChatId(final UUID chatId) {
        this.chatId = chatId;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ChatType getType() {
        return this.type;
    }

    public void setType(final ChatType type) {
        this.type = type;
    }

    public ChatStatus getStatus() {
        return this.status;
    }

    public void setStatus(final ChatStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public LocalDateTime getLastActivity() {
        return this.lastActivity;
    }

    public void setLastActivity(final LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Set<UUID> getParticipants() {
        return this.participants;
    }

    public void setParticipants(final Set<UUID> participants) {
        this.participants = participants;
    }

    public Set<UUID> getMessages() {
        return this.messages;
    }

    public void setMessages(final Set<UUID> messages) {
        this.messages = messages;
    }
    
}
