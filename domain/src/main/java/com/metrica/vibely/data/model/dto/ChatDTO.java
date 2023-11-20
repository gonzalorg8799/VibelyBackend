package com.metrica.vibely.data.model.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.util.DeepCopyGenerator;

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
        this.setChatId      (null);
        this.setParticipants(null);
        this.setMessages    (null);
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
		super();
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
        boolean inserted = false;
        if (participant != null) {
            inserted = this.participants.add(participant);
        }
        return inserted;
    }
    
    /**
     * Remove a participant from the chat.
     * 
     * @param participant the user to remove
     */
    public boolean removeParticipant(final UUID participant) {
        boolean removed = false;
        if (participant != null) {
            removed = this.participants.remove(participant);
        }
        return removed;
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
        if (chatId == null) {
            this.chatId = UUID.randomUUID();
        } else {
            this.chatId = chatId;
        }
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final LocalDateTime creationDate) {
        if (creationDate == null) {
            this.creationDate = LocalDateTime.now();
        } else {
            this.creationDate = creationDate;
        }
    }

    public ChatType getType() {
        return this.type;
    }

    public void setType(final ChatType type) {
        if (type == null) {
            this.type = ChatType.DIRECT_MESSAGE;
        } else {
            this.type = type;
        }
    }

    public ChatStatus getStatus() {
        return this.status;
    }

    public void setStatus(final ChatStatus status) {
        if (status == null) {
            this.status = ChatStatus.ACTIVE;
        } else {
            this.status = status;
        }
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
        if (lastActivity == null) {
            this.lastActivity = LocalDateTime.now();
        } else {
            this.lastActivity = lastActivity;
        }
    }

    public Set<UUID> getParticipants() {
        return this.participants;
    }

    public void setParticipants(final Set<UUID> participants) {
        this.participants = new HashSet<>();
        if (participants != null) {
            this.participants.addAll(participants);
        }
    }

    public Set<UUID> getMessages() {
        return this.messages;
    }

    public void setMessages(final Set<UUID> messages) {
        this.messages = new HashSet<>();
        if (messages != null) {
            this.messages.addAll(messages);
        }
    }
    
    
}
