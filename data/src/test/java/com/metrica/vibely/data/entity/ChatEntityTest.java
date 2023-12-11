package com.metrica.vibely.data.entity;

import com.metrica.vibely.data.model.enumerator.ChatState;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Chat Entity Test</h1>
 * 
 * @since 2023-11-16
 * @version 1.0
 * @author Alex
 */
class ChatEntityTest {

    // <<-CONSTANTS->>
    private static final String TITLE = "Title";
    private static final LocalDateTime CREATION_DATE = LocalDateTime.now();
    private static final LocalDateTime LAST_ACTIVITY = LocalDateTime.now();
    private static final ChatType   TYPE   = ChatType.DIRECT_MESSAGE;
    private static final ChatStatus STATUS = ChatStatus.ACTIVE;
    private static final ChatState  STATE  = ChatState.ENABLED;
    
    // <<-METHODS->>
    @Test
    @Tag("Constructors")
    void voidConstructorTest() {
        UUID chatId = UUID.randomUUID();
        Set<User>    participants = new HashSet<>();
        Set<Message> messages     = new HashSet<>();
        
        Chat chat = new Chat();
        
        chat.setChatId      (chatId);
        chat.setCreationDate(CREATION_DATE);
        chat.setType        (TYPE);
        chat.setStatus      (STATUS);
        chat.setState		(STATE);
        chat.setTitle       (TITLE);
        chat.setLastActivity(LAST_ACTIVITY);
        chat.setParticipants(participants);
        chat.setMessages    (messages);

        assertEquals(chatId,        chat.getChatId());
        assertEquals(CREATION_DATE, chat.getCreationDate());
        assertEquals(TYPE,          chat.getType());
        assertEquals(STATUS,        chat.getStatus());
        assertEquals(STATE,			chat.getState());		
        assertEquals(TITLE,         chat.getTitle());
        assertEquals(LAST_ACTIVITY, chat.getLastActivity());
        assertEquals(participants,  chat.getParticipants());
        assertEquals(messages,      chat.getMessages());
    }
    
    @Test
    @Tag("Constructors")
    void fullArgsConstructorTest() {
        UUID chatId = UUID.randomUUID();
        Set<User>    participants = new HashSet<>();
        Set<Message> messages     = new HashSet<>();
        
        Chat chat = new Chat(
                chatId,
                CREATION_DATE,
                TYPE,
                STATUS,
                STATE,
                TITLE,
                LAST_ACTIVITY,
                participants,
                messages);

        assertEquals(chatId,        chat.getChatId());
        assertEquals(CREATION_DATE, chat.getCreationDate());
        assertEquals(TYPE,          chat.getType());
        assertEquals(STATUS,        chat.getStatus());
        assertEquals(STATE,         chat.getState());
        assertEquals(TITLE,         chat.getTitle());
        assertEquals(LAST_ACTIVITY, chat.getLastActivity());
        assertEquals(participants,  chat.getParticipants());
        assertEquals(messages,      chat.getMessages());
    }

    @Test
    @Tag("Default values")
    void notNullableFieldsAndDefaultValuesTest() {
        Chat chat = new Chat();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        
        chat.setChatId      (null);
        chat.setCreationDate(null);
        chat.setType        (null);
        chat.setStatus      (null);
        chat.setState       (null);
        chat.setLastActivity(null);

        assertNotNull(chat.getChatId());
        assertEquals(now.format(formatter),   chat.getCreationDate().format(formatter));
        assertEquals(now.format(formatter),   chat.getLastActivity().format(formatter));
        assertEquals(ChatType.DIRECT_MESSAGE, chat.getType());
        assertEquals(ChatStatus.ACTIVE,       chat.getStatus());
        assertEquals(ChatState.ENABLED,       chat.getState());
    }

    @Test
    @Tag("Equality")
    void basicEqualityTest() {
        Chat chat = new Chat();
        
        assertEquals   (chat, chat);
        assertNotEquals(chat, null);
        assertNotEquals(chat, "");
    }
    
    @Test
    @Tag("Equality")
    void equalityByIdAndHashCodeTest() {
        Chat chat1 = new Chat();
        Chat chat2 = new Chat();
        
        // Both have the same ID
        UUID messageId = UUID.randomUUID();
        chat1.setChatId(messageId);
        chat2.setChatId(messageId);
        
        assertEquals(chat1, chat2);
        assertEquals(chat1.hashCode(), chat2.hashCode());
    }
    
    @Test
    @Tag("Equality")
    void equalityWithDeepCopyTest() {
        Chat chat1 = new Chat();
        Chat chat2 = chat1.deepCopy();
        
        // Both have the same ID
        UUID messageId = UUID.randomUUID();
        chat1.setChatId(messageId);
        chat2.setChatId(messageId);
        
        assertEquals(chat1, chat2);
        assertEquals(chat1.hashCode(), chat2.hashCode());
    }
    
    @Test
    @Tag("Equality")
    void inequalityByFieldsTest() {
        Chat chat1 = new Chat();
        Chat chat2 = new Chat();

        // Not the same ID
        chat1.setCreationDate(CREATION_DATE);
        chat1.setType        (TYPE);
        chat1.setStatus      (STATUS);
        chat1.setTitle       (TITLE);
        chat1.setLastActivity(LAST_ACTIVITY);

        chat2.setCreationDate(CREATION_DATE);
        chat2.setType        (TYPE);
        chat2.setStatus      (STATUS);
        chat2.setTitle       (TITLE);
        chat2.setLastActivity(LAST_ACTIVITY);

        assertNotEquals(chat1, chat2);
    }
    
    @Test
    @Tag("Participant")
    void addParticipantTest() {
        Chat chat = new Chat();
        User participant = new User();
        
        // The first time we add a participant
        assertTrue(chat.addParticipant(participant));
        
        // Since the user already in the set
        assertFalse(chat.addParticipant(participant));
        
        // Trying to add a null participant
        assertFalse(chat.addParticipant(null));
    }
    
    @Test
    @Tag("Participant")
    void removeParticipantTest() {
        Chat chat = new Chat();
        User participant = new User();
        
        chat.addParticipant(participant);
        
        // The first time we remove a participant
        assertTrue(chat.removeParticipant(participant));
        
        // Since the user does not exist in the set anymore
        assertFalse(chat.removeParticipant(participant));
        
        // Trying to remove a null participant
        assertFalse(chat.removeParticipant(null));
    }
    
}
