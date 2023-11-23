package com.metrica.vibely.data.mapper;

import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.Post;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;
import com.metrica.vibely.data.model.mapper.ChatMapper;

import org.junit.jupiter.api.Tag;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Chat Mapper Test</h1>
 * 
 * @since 2023-11-23
 * @version 1.0
 * @author Adrian
 */
class ChatMapperTest {

    // <<-CONSTANTS->>
    private static final UUID CHAT_ID = UUID.randomUUID();
    private static final LocalDateTime CREATION_DATE = LocalDateTime.now();
    private static final ChatType TYPE = ChatType.GROUP;
    private static final ChatStatus STATUS = ChatStatus.ACTIVE;
    private static final String TITLE = "Test Chat";
    private static final LocalDateTime LAST_ACTIVITY = LocalDateTime.now();
    

    // <<-METHODS->>
    private ChatDTO createChatDTO() {
        ChatDTO chatDTO = new ChatDTO();
        
        chatDTO.setChatId(CHAT_ID);
        chatDTO.setCreationDate(CREATION_DATE);
        chatDTO.setType(TYPE);
        chatDTO.setStatus(STATUS);
        chatDTO.setTitle(TITLE);
        chatDTO.setLastActivity(LAST_ACTIVITY);

        return chatDTO;
    }
    
    private Chat createChat() {
        Chat chat = new Chat();
        
        chat.setChatId(CHAT_ID);
        chat.setCreationDate(CREATION_DATE);
        chat.setType(TYPE);
        chat.setStatus(STATUS);
        chat.setTitle(TITLE);
        chat.setLastActivity(LAST_ACTIVITY);

        return chat;
    }


    @Test
    @Tag("toEntity")
    void toEntityTest() {
        ChatDTO chatDTO = createChatDTO();

        Set<User> participants = new HashSet<>();
        Set<Message> messages = new HashSet<>();

        Chat chat = ChatMapper.toEntity(chatDTO, participants, messages);

        assertEquals(chatDTO.getChatId(), chat.getChatId());
        assertEquals(chatDTO.getCreationDate(), chat.getCreationDate());
        assertEquals(chatDTO.getType(), chat.getType());
        assertEquals(chatDTO.getStatus(), chat.getStatus());
        assertEquals(chatDTO.getTitle(), chat.getTitle());
        assertEquals(chatDTO.getLastActivity(), chat.getLastActivity());
        
        assertEquals(participants, chat.getParticipants());
        assertEquals(messages, chat.getMessages());
    }

    @Test
    @Tag("toDTO")
    void toDTOTest() {
        Chat chat = createChat();
        
        Set<User> participants = new HashSet<>();
        Set<Message> messages = new HashSet<>();
        
        chat.setParticipants(participants);
        chat.setMessages(messages);
       
        ChatDTO chatDTO = ChatMapper.toDTO(chat);

        assertEquals(chat.getChatId(), chatDTO.getChatId());
        assertEquals(chat.getCreationDate(), chatDTO.getCreationDate());
        assertEquals(chat.getType(), chatDTO.getType());
        assertEquals(chat.getStatus(), chatDTO.getStatus());
        assertEquals(chat.getTitle(), chatDTO.getTitle());
        assertEquals(chat.getLastActivity(), chatDTO.getLastActivity());
        
        assertEquals(participants.stream()
        						 .map(User::getUserId)
        						 .collect(Collectors.toSet()),
        			 chatDTO.getParticipants());
        
        assertEquals(messages.stream()
        					 .map(Message::getMessageId)
        					 .collect(Collectors.toSet()),
        			 chatDTO.getMessages());
    }
}
