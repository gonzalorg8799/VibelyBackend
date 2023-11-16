package com.metrica.vibely.data.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.model.enumerator.MessageStatus;

/**
 * <h1>Message Entity Test</h1>
 * 
 * @since 2023-11-16
 * @version 1.0
 * @author Alex
 */
public class MessageEntityTest {

    // <<-FIELD->>
    private Message message;

    // <<-METHODS->>
    @BeforeEach
    void setUp() {
        message = new Message();
    }
    
    @Test
    void testGettersAndSetters() {
        UUID messageId = UUID.randomUUID();
        LocalDateTime creationTimestamp = LocalDateTime.now();
        MessageStatus status = MessageStatus.PENDING;
        String content = "test message";
        User sender = new User();
        Chat chat = new Chat();
        
        message.setMessageId(messageId);
        message.setCreationTimestamp(creationTimestamp);
        message.setStatus(status);
        message.setContent(content);
        message.setSender(sender);
        message.setChat(chat);

        assertEquals(messageId,         message.getMessageId());
        assertEquals(creationTimestamp, message.getCreationTimestamp());
        assertEquals(status,            message.getStatus());
        assertEquals(content,           message.getContent());
        assertEquals(sender,            message.getSender());
        assertEquals(chat,              message.getChat());
        
        Message testMessage = new Message(
                messageId,
                creationTimestamp,
                status,
                content,
                sender,
                chat);
        
        assertEquals(message, testMessage);
    }
    
    @Test
    void testNotNullableFields() {
        message.setMessageId(null);
        message.setCreationTimestamp(null);
        message.setStatus(null);

        assertNotNull(message.getMessageId());
        assertNotNull(message.getCreationTimestamp());
        assertNotNull(message.getStatus());
    }
    
    @Test
    void testDefaultValues() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        
        message.setCreationTimestamp(null);
        message.setStatus(null);
        
        assertEquals(now.format(formatter), message.getCreationTimestamp().format(formatter));
        assertEquals(MessageStatus.PENDING, message.getStatus());
    }
    
    @Test
    void testIdEquality() {
        UUID messageId = UUID.randomUUID();
        message.setMessageId(messageId);
        
        Message testChat = new Message();
        testChat.setMessageId(messageId);
        
        assertEquals(testChat, message);
    }
    
    @Test
    void testIdNullEquality() {
        LocalDateTime now = LocalDateTime.now();
        String content = "test title";
        User sender = new User();
        Chat chat = new Chat();
        
        message.setCreationTimestamp(now);
        message.setStatus(MessageStatus.PENDING);
        message.setContent(content);
        message.setSender(sender);
        message.setChat(chat);
        
        
        Message testMessage = new Message();
        testMessage.setCreationTimestamp(now);
        testMessage.setStatus(MessageStatus.PENDING);
        testMessage.setContent(content);
        testMessage.setSender(sender);
        testMessage.setChat(chat);
        
        assertNotEquals(testMessage, message);
    }
    
    @Test
    void testBasicEquality() {
        assertEquals(message, message);
        assertNotEquals(message, null);
        assertNotEquals(message, "");
    }
    
}
