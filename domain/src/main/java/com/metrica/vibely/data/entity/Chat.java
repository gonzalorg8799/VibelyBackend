package com.metrica.vibely.data.entity;

import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.util.Copyable;
import com.metrica.vibely.data.util.DeepCopyGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * <h1>Chat Entity</h1>
 * 
 * @since 2023-11-13
 * @version 1.0
 * @author Adrian, Alex
 */
@Entity
public class Chat implements Copyable<Chat> {

    // <<-FIELDS->>

    // Basic
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "chat_id")
    private UUID chatId;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Enumerated(value = EnumType.ORDINAL)
    private ChatType type;
    @Enumerated(value = EnumType.ORDINAL)
    private ChatStatus status;
    private String title;
    @Column(name = "last_activity")
    private LocalDateTime lastActivity;

    // Relations
    @ManyToMany
    @JoinTable(name = "user_chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            foreignKey = @ForeignKey(name = "fk_user-chat_chat"),
            inverseForeignKey = @ForeignKey(name = "fk_user-chat_user"))
    private Set<User> participants;
    @OneToMany(mappedBy = "chat")
    private Set<Message> messages;
    
    // <<-CONSTRUCTORS->>
    public Chat() {
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
        this.setChatId      (chatId);
        this.setLastActivity(lastActivity);
        this.setType        (type);
        this.setStatus      (status);
        this.setTitle       (title);
        this.setCreationDate(creationDate);
        this.setParticipants(participants);
        this.setMessages    (messages);
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
            inserted = this.participants.add(participant.deepCopy());
        }
        return inserted;
    }

    /**
     * Remove a participant from the chat.
     * 
     * @param participant the user to remove
     */
    public boolean removeParticipant(final User participant) {
        boolean removed = false;
        if (participant != null) {
            removed = this.participants.remove(participant.deepCopy());
        }
        return removed;
    }
    
    @Override
    public Chat deepCopy() {
        Chat copy = new Chat();
        
        copy.setChatId      (this.chatId);
        copy.setLastActivity(this.lastActivity);
        copy.setType        (this.type);
        copy.setStatus      (this.status);
        copy.setTitle       (this.title);
        copy.setCreationDate(this.creationDate);
//        copy.setParticipants(this.participants);
//        copy.setMessages    (this.messages);
        
        return copy;
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
        return DeepCopyGenerator.generateCopy(this.participants);
    }

    public void setParticipants(final Set<User> participants) {
        this.participants = new HashSet<>();
        if (participants != null) {
            this.participants.addAll(DeepCopyGenerator.generateCopy(participants));
        }
    }

    public Set<Message> getMessages() {
        return DeepCopyGenerator.generateCopy(this.messages);
    }

    public void setMessages(final Set<Message> messages) {
        this.messages = new HashSet<>();
        if (messages != null) {
            this.messages.addAll(DeepCopyGenerator.generateCopy(messages));
        }
    }

}