package com.metrica.vibely.data.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Column(name = "chat_id")
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
        this.setParticipants(null);
        this.setMessages(null);
    }

    public Chat(
            UUID chatId,
            LocalDateTime lastActivity,
            ChatType type,
            ChatStatus status,
            String title,
            LocalDateTime creationDate,
            Set<User> participants,
            Set<Message> messages) {
        this.setChatId(chatId);
        this.setLastActivity(lastActivity);
        this.setType(type);
        this.setStatus(status);
        this.setTitle(title);
        this.setCreationDate(creationDate);
        this.setParticipants(participants);
        this.setMessages(messages);
    }
    
    /**
     * Constructs a copy of the given entity.
     * 
     * @param chat the chat to copy
     */
    public Chat(Chat chat) {
        this.setChatId      (chat.getChatId());
        this.setLastActivity(chat.getLastActivity());
        this.setType        (chat.getType());
        this.setStatus      (chat.getStatus());
        this.setTitle       (chat.getTitle());
        this.setCreationDate(chat.getCreationDate());
        this.setParticipants(chat.getParticipants());
        this.setMessages    (chat.getMessages());
    }
    
    // <<-METHODS->>
    /**
     * Add a new participant to the chat.
     * 
     * @param participant the new user
     */
    public boolean addParticipant(final User participant) {
        boolean inserted = false;
        if (participant != null) {
            inserted = this.participants.add(new User(participant));
        }
        return inserted;
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
        Chat other = (Chat) obj;
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

    public Set<User> getParticipants() {
        return this.participants.stream()
                .map(User::new)
                .collect(Collectors.toSet());
    }

    public void setParticipants(final Set<User> participants) {
        this.participants = new HashSet<>();
        if (participants != null) {
            this.participants.addAll(participants.stream()
                    .map(User::new)
                    .collect(Collectors.toSet()));
        }
    }

    public Set<Message> getMessages() {
        return this.messages.stream()
                .map(Message::new)
                .collect(Collectors.toSet());
    }

    public void setMessages(final Set<Message> messages) {
        this.messages = new HashSet<>();
        if (messages != null) {
            this.messages.addAll(messages.stream()
                    .map(Message::new)
                    .collect(Collectors.toSet()));
        }
    }
    
}
