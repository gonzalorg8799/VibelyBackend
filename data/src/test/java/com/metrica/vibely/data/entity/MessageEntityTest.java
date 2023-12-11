package com.metrica.vibely.data.entity;

import com.metrica.vibely.data.model.enumerator.MessageState;
import com.metrica.vibely.data.model.enumerator.MessageStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * <h1>Message Entity Test</h1>
 * 
 * @since 2023-11-16
 * @version 1.0
 * @author Alex, Gonzalo
 */
class MessageEntityTest {

    // <<-CONSTANTS->>
    private static final String CONTENT = "content";
    private static final MessageStatus STATUS = MessageStatus.PENDING;
    private static final MessageState STATE = MessageState.ENABLED;
    private static final LocalDateTime CREATION_TIMESTAMP = LocalDateTime.now();

    // <<-METHODS->>
    @Test
    @Tag("Constructors")
    void voidConstructorTest() {
        UUID messageId = UUID.randomUUID();
        Chat chat = new Chat();
        User sender = new User();
        
        Message message = new Message();

        message.setMessageId        (messageId);
        message.setCreationTimestamp(CREATION_TIMESTAMP);
        message.setStatus           (STATUS);
        message.setState			(STATE);
        message.setContent          (CONTENT);
        message.setChat             (chat);
        message.setSender           (sender);

        assertEquals(messageId,          message.getMessageId());
        assertEquals(CREATION_TIMESTAMP, message.getCreationTimestamp());
        assertEquals(STATUS,             message.getStatus());
        assertEquals(STATE,				 message.getState());
        assertEquals(CONTENT,            message.getContent());
        assertEquals(chat,               message.getChat());
        assertEquals(sender,             message.getSender());
    }
    
    @Test
    @Tag("Constructors")
    void fullArgsConstructorTest() {
        UUID messageId = UUID.randomUUID();
        Chat chat = new Chat();
        User sender = new User();
        
        Message message = new Message(
                messageId,
                CREATION_TIMESTAMP,
                STATUS,
                STATE,
                CONTENT,
                chat,
                sender);

        assertEquals(messageId,          message.getMessageId());
        assertEquals(CREATION_TIMESTAMP, message.getCreationTimestamp());
        assertEquals(STATUS,             message.getStatus());
        assertEquals(STATE, 			 message.getState());	
        assertEquals(CONTENT,            message.getContent());
        assertEquals(chat,               message.getChat());
        assertEquals(sender,             message.getSender());
    }

    @Test
    @Tag("Default values")
    void notNullableFieldsAndDefaultValuesTest() {
        Message message = new Message();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        message.setMessageId        (null);
        message.setCreationTimestamp(null);
        message.setStatus           (null);
        message.setState			(null);

        assertNotNull(message.getMessageId());
        assertEquals(now.format(formatter),  message.getCreationTimestamp().format(formatter));
        assertEquals(MessageStatus.PENDING,  message.getStatus());
        assertEquals(MessageState.ENABLED, 	 message.getState());
        
    }

    @Test
    @Tag("Equality")
    void basicEqualityTest() {
        Message message = new Message();
        
        assertEquals   (message, message);
        assertNotEquals(message, null);
        assertNotEquals(message, "");
    }
    
    @Test
    @Tag("Equality")
    void equalityByIdAndHashCodeTest() {
        Message message1 = new Message();
        Message message2 = new Message();
        
        // Both have the same ID
        UUID messageId = UUID.randomUUID();
        message1.setMessageId(messageId);
        message2.setMessageId(messageId);
        
        assertEquals(message1, message2);
        assertEquals(message1.hashCode(), message2.hashCode());
    }
    
    @Test
    @Tag("Equality")
    void equalityWithDeepCopyTest() {
        Message message1 = new Message();
        Message message2 = message1.deepCopy();
        
        // Both have the same ID
        UUID messageId = UUID.randomUUID();
        message1.setMessageId(messageId);
        message2.setMessageId(messageId);
        
        assertEquals(message1, message2);
        assertEquals(message1.hashCode(), message2.hashCode());
    }
    
    @Test
    void testIdNullEquality() {
        Message message1 = new Message();
        Message message2 = new Message();
        
        message1.setCreationTimestamp(CREATION_TIMESTAMP);
        message1.setStatus(STATUS);
        message1.setState(STATE);
        message1.setContent(CONTENT);
        
        
        message2.setCreationTimestamp(CREATION_TIMESTAMP);
        message2.setStatus(STATUS);
        message2.setState(STATE);
        message2.setContent(CONTENT);
        
        assertNotEquals(message1, message2);
    }
    
}
