package com.metrica.vibely.data.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <h1>Chat Entity Test</h1>
 * 
 * @since 2023-11-16
 * @version 1.0
 * @author Alex
 */
public class ChatEntityTest {

    // <<-FIELD->>
    private Chat chat;

    // <<-METHODS->>
    @BeforeEach
    void setUp() {
        chat = new Chat();
    }
    
    @Test
    void testGettersAndSetters() {
        UUID   chatId = UUID.randomUUID();
        String title  = "test";
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime lastActivity = LocalDateTime.now();
        ChatType   type   = ChatType.DIRECT_MESSAGE;
        ChatStatus status = ChatStatus.ACTIVE;
        Set<User> participants = new HashSet<>();
        Set<Message> messages = new HashSet<>();
        
        chat.setChatId(chatId);
        chat.setChatId(chatId);
        chat.setLastActivity(lastActivity);
        chat.setType(type);
        chat.setStatus(status);
        chat.setTitle(title);
        chat.setCreationDate(creationDate);
        chat.setParticipants(participants);
        chat.setMessages(messages);

        assertEquals(chatId,       chat.getChatId());
        assertEquals(creationDate, chat.getCreationDate());
        assertEquals(type,         chat.getType());
        assertEquals(status,       chat.getStatus());
        assertEquals(title,        chat.getTitle());
        assertEquals(lastActivity, chat.getLastActivity());
        assertEquals(participants, chat.getParticipants());
        assertEquals(messages,     chat.getMessages());
        
        Chat testChat = new Chat(
                chatId,
                lastActivity,
                type,
                status,
                title,
                creationDate,
                participants,
                messages);
        
        assertEquals(chat, testChat);
    }
    
    @Test
    void testNotNullableFields() {
        chat.setChatId      (null);
        chat.setCreationDate(null);
        chat.setType        (null);
        chat.setStatus      (null);
        chat.setLastActivity(null);

        assertNotNull(chat.getChatId());
        assertNotNull(chat.getCreationDate());
        assertNotNull(chat.getType());
        assertNotNull(chat.getStatus());
        assertNotNull(chat.getLastActivity());
    }
    
    @Test
    void testDefaultValues() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDateTime = LocalDateTime.now();

        chat.setCreationDate(null);
        chat.setType        (null);
        chat.setStatus      (null);
        chat.setLastActivity(null);
        
        assertEquals(currentDateTime.format(formatter), chat.getCreationDate().format(formatter));
        assertEquals(currentDateTime.format(formatter), chat.getLastActivity().format(formatter));
        assertEquals(ChatType.DIRECT_MESSAGE, chat.getType());
        assertEquals(ChatStatus.ACTIVE, chat.getStatus());
    }
    
    @Test
    void testIdEquality() {
        UUID chatId = UUID.randomUUID();
        chat.setChatId(chatId);
        
        Chat testChat = new Chat();
        testChat.setChatId(chatId);
        
        assertEquals(testChat, chat);
    }
    
    @Test
    void testIdNullEquality() {
        LocalDateTime creationDate = LocalDateTime.now();
        String title = "test title";
        
        chat.setCreationDate(creationDate);
        chat.setType(ChatType.DIRECT_MESSAGE);
        chat.setStatus(ChatStatus.ACTIVE);
        chat.setTitle(title);
        
        Chat testChat = new Chat();
        testChat.setCreationDate(creationDate);
        testChat.setType(ChatType.DIRECT_MESSAGE);
        testChat.setStatus(ChatStatus.ACTIVE);
        testChat.setTitle(title);
        
        assertNotEquals(testChat, chat);
    }
    
    @Test
    void testBasicEquality() {
        assertEquals(chat, chat);
        assertNotEquals(chat, null);
        assertNotEquals(chat, "");
    }
    
    
}
