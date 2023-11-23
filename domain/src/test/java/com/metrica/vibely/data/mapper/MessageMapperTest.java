package com.metrica.vibely.data.mapper;


import org.junit.jupiter.api.Test;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.enumerator.MessageStatus;
import com.metrica.vibely.data.model.mapper.MessageMapper;

import org.junit.jupiter.api.Tag;

import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Message Mapper Test</h1>
 * 
 * @since 2023-11-23
 * @version 1.0
 * @author Adrian
 */
class MessageMapperTest {

    // <--CONSTANTS-->
    private static final UUID MESSAGE_ID = UUID.randomUUID();
    private static final LocalDateTime CREATION_TIMESTAMP = LocalDateTime.now();
    private static final MessageStatus STATUS = MessageStatus.SENT;
    private static final String CONTENT = "This is a test message content";
    private static final UUID CHAT_ID = UUID.randomUUID();
    private static final UUID SENDER_ID = UUID.randomUUID();

    // <--METHODS-->
    private MessageDTO createMessageDTO() {
        MessageDTO messageDTO = new MessageDTO();
        
        messageDTO.setMessageId(MESSAGE_ID);
        messageDTO.setCreationTimestamp(CREATION_TIMESTAMP);
        messageDTO.setStatus(STATUS);
        messageDTO.setContent(CONTENT);
        messageDTO.setChat(CHAT_ID);
        messageDTO.setSender(SENDER_ID);
        
        return messageDTO;
    }
    
    
    private Message createMessage() {
        Message message = new Message();
        
        message.setMessageId(MESSAGE_ID);
        message.setCreationTimestamp(CREATION_TIMESTAMP);
        message.setStatus(STATUS);
        message.setContent(CONTENT);

        return message;
    }


    private Chat createChat() {
        Chat chat = new Chat();
        
        chat.setChatId(CHAT_ID);
        
        return chat;
    }

    private User createSender() {
        User sender = new User();
        
        sender.setUserId(SENDER_ID);
        
        return sender;
    }

    @Test
    @Tag("toEntity")
    void toEntityTest() {
        MessageDTO messageDTO = createMessageDTO();
        Chat chat = createChat();
        User sender = createSender();

        Message message = MessageMapper.toEntity(messageDTO, chat, sender);

        assertEquals(messageDTO.getMessageId(), message.getMessageId());
        assertEquals(messageDTO.getCreationTimestamp(), message.getCreationTimestamp());
        assertEquals(messageDTO.getStatus(), message.getStatus());
        assertEquals(messageDTO.getContent(), message.getContent());
        assertEquals(chat, message.getChat());
        assertEquals(sender, message.getSender());
    }

    @Test
    @Tag("toDTO")
    void toDTOTest() {
        Message message = createMessage();
        
        Chat chat = createChat();
        User sender = createSender();
        
        message.setChat(chat);
        message.setSender(sender);

        MessageDTO messageDTO = MessageMapper.toDTO(message);

        assertEquals(message.getMessageId(), messageDTO.getMessageId());
        assertEquals(message.getCreationTimestamp(), messageDTO.getCreationTimestamp());
        assertEquals(message.getStatus(), messageDTO.getStatus());
        assertEquals(message.getContent(), messageDTO.getContent());
        assertEquals(chat.getChatId(), messageDTO.getChat());
        assertEquals(sender.getUserId(), messageDTO.getSender());
    }
}

