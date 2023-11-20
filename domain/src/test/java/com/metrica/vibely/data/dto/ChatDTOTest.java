package com.metrica.vibely.data.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

/**
 * <h1>Chat DTO Test</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Adri
 */
class ChatDTOTest {

	// <<-CONSTANTS->>
    private static final String TITLE = "Title";
    private static final LocalDateTime CREATION_DATE = LocalDateTime.now();
    private static final LocalDateTime LAST_ACTIVITY = LocalDateTime.now();
    private static final ChatType   TYPE   = ChatType.DIRECT_MESSAGE;
    private static final ChatStatus STATUS = ChatStatus.ACTIVE;
    
    // <<-METHODS->>
    @Test
    @Tag("Constructors")
    void voidConstructorTest() {
        UUID chatId = UUID.randomUUID();
        Set<UUID>    participants = new HashSet<>();
        Set<UUID> messages     = new HashSet<>();
        
        ChatDTO chat = new ChatDTO();
        
        chat.setChatId      (chatId);
        chat.setCreationDate(CREATION_DATE);
        chat.setType        (TYPE);
        chat.setStatus      (STATUS);
        chat.setTitle       (TITLE);
        chat.setLastActivity(LAST_ACTIVITY);
        chat.setParticipants(participants);
        chat.setMessages    (messages);

        assertEquals(chatId,        chat.getChatId());
        assertEquals(CREATION_DATE, chat.getCreationDate());
        assertEquals(TYPE,          chat.getType());
        assertEquals(STATUS,        chat.getStatus());
        assertEquals(TITLE,         chat.getTitle());
        assertEquals(LAST_ACTIVITY, chat.getLastActivity());
        assertEquals(participants,  chat.getParticipants());
        assertEquals(messages,      chat.getMessages());
    }
    
    @Test
    @Tag("Constructors")
    void fullArgsConstructorTest() {
        UUID chatId = UUID.randomUUID();
        Set<UUID>    participants = new HashSet<>();
        Set<UUID> messages     = new HashSet<>();
        
        ChatDTO chat = new ChatDTO(
                chatId,
                CREATION_DATE,
                TYPE,
                STATUS,
                TITLE,
                LAST_ACTIVITY,
                participants,
                messages);

        assertEquals(chatId,        chat.getChatId());
        assertEquals(CREATION_DATE, chat.getCreationDate());
        assertEquals(TYPE,          chat.getType());
        assertEquals(STATUS,        chat.getStatus());
        assertEquals(TITLE,         chat.getTitle());
        assertEquals(LAST_ACTIVITY, chat.getLastActivity());
        assertEquals(participants,  chat.getParticipants());
        assertEquals(messages,      chat.getMessages());
    }

    @Test
    @Tag("Default values")
    void notNullableFieldsAndDefaultValuesTest() {
    	ChatDTO chat = new ChatDTO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        
        chat.setChatId      (null);
        chat.setCreationDate(null);
        chat.setType        (null);
        chat.setStatus      (null);
        chat.setLastActivity(null);

        assertNotNull(chat.getChatId());
        assertEquals(now.format(formatter),   chat.getCreationDate().format(formatter));
        assertEquals(now.format(formatter),   chat.getLastActivity().format(formatter));
        assertEquals(ChatType.DIRECT_MESSAGE, chat.getType());
        assertEquals(ChatStatus.ACTIVE,       chat.getStatus());
    }

    @Test
    @Tag("Equality")
    void basicEqualityTest() {
    	ChatDTO chat = new ChatDTO();
        
        assertEquals   (chat, chat);
        assertNotEquals(chat, null);
        assertNotEquals(chat, "");
    }
    
    @Test
    @Tag("Equality")
    void equalityByIdAndHashCodeTest() {
    	ChatDTO chat1 = new ChatDTO();
    	ChatDTO chat2 = new ChatDTO();
        
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
    	ChatDTO chat1 = new ChatDTO();
    	ChatDTO chat2 = new ChatDTO();

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
    	ChatDTO chat = new ChatDTO();
        UUID participant = UUID.randomUUID();
        
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
    	ChatDTO chat = new ChatDTO();
        UUID participant = UUID.randomUUID();
        
        chat.addParticipant(participant);
        
        // The first time we remove a participant
        assertTrue(chat.removeParticipant(participant));
        
        // Since the user does not exist in the set anymore
        assertFalse(chat.removeParticipant(participant));
        
        // Trying to remove a null participant
        assertFalse(chat.removeParticipant(null));
    }
}
