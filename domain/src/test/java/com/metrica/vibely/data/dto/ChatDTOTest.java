package com.metrica.vibely.data.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatState;
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
    private static final ChatState STATE = ChatState.ENABLED;
    
    // <<-METHODS->>
    @Test
    @Tag("Constructors")
    void voidConstructorTest() {
        UUID chatId = UUID.randomUUID();
        Set<UUID> participants = new HashSet<>();
        Set<UUID> messages     = new HashSet<>();
        
        ChatDTO chat = new ChatDTO();
        
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
        Set<UUID> participants = new HashSet<>();
        Set<UUID> messages     = new HashSet<>();
        
        ChatDTO chat = new ChatDTO(
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
        assertEquals(STATE, 		chat.getState());
        assertEquals(TITLE,         chat.getTitle());
        assertEquals(LAST_ACTIVITY, chat.getLastActivity());
        assertEquals(participants,  chat.getParticipants());
        assertEquals(messages,      chat.getMessages());
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
    	UUID uuid = UUID.randomUUID();

        // Both the same ID
    	chat1.setChatId(uuid);
        chat1.setCreationDate(CREATION_DATE);
        chat1.setType        (TYPE);
        chat1.setStatus      (STATUS);
        chat1.setState		 (STATE);
        chat1.setTitle       (TITLE);
        chat1.setLastActivity(LAST_ACTIVITY);

    	chat2.setChatId(uuid);
        chat2.setCreationDate(CREATION_DATE);
        chat2.setType        (TYPE);
        chat2.setStatus      (STATUS);
        chat2.setState		 (STATE);
        chat2.setTitle       (TITLE);
        chat2.setLastActivity(LAST_ACTIVITY);

        assertEquals(chat1, chat2);
        assertEquals(chat1.hashCode(), chat2.hashCode());
    }
    
    @Test
    @Tag("Equality")
    void inequalityByFieldsTest() {
    	ChatDTO chat1 = new ChatDTO();
    	ChatDTO chat2 = new ChatDTO();

        // Not the same ID
    	chat1.setChatId(UUID.randomUUID());
        chat1.setCreationDate(CREATION_DATE);
        chat1.setType        (TYPE);
        chat1.setStatus      (STATUS);
        chat1.setState		 (STATE);
        chat1.setTitle       (TITLE);
        chat1.setLastActivity(LAST_ACTIVITY);

    	chat2.setChatId(UUID.randomUUID());
        chat2.setCreationDate(CREATION_DATE);
        chat2.setType        (TYPE);
        chat2.setStatus      (STATUS);
        chat2.setState		 (STATE);
        chat2.setTitle       (TITLE);
        chat2.setLastActivity(LAST_ACTIVITY);

        assertNotEquals(chat1, chat2);
    }
    
    @Test
    @Tag("Participant")
    void addParticipantTest() {
    	ChatDTO chat = new ChatDTO();
        UUID participant = UUID.randomUUID();
        chat.setParticipants(new HashSet<>());
        
        assertTrue(chat.addParticipant(participant));
       
    }
    
    @Test
    @Tag("Participant")
    void removeParticipantTest() {
    	ChatDTO chat = new ChatDTO();
        UUID participant = UUID.randomUUID();
        chat.setParticipants(new HashSet<>());

        chat.addParticipant(participant);
        
        assertTrue(chat.removeParticipant(participant));
    }
}
