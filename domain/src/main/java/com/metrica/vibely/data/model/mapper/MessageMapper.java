package com.metrica.vibely.data.model.mapper;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.MessageDTO;

/**
 * @since 2023-11-22
 * @author Adrian
 * @version 1.0
 *
 */
public class MessageMapper {

	public static Message toEntity(MessageDTO messageDTO) {
        Message message = new Message();

        // Mapping Basics
        message.setMessageId(messageDTO.getMessageId());
        message.setCreationTimestamp(messageDTO.getCreationTimestamp());
        message.setStatus(messageDTO.getStatus());
        message.setContent(messageDTO.getContent());

        // Mapping Relations
        message.setChat(new Chat(messageDTO.getChat()));
        message.setSender(new User(messageDTO.getSender()));

        return message;
    }

    public static MessageDTO toDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();

        // Mapping Basics
        messageDTO.setMessageId(message.getMessageId());
        messageDTO.setCreationTimestamp(message.getCreationTimestamp());
        messageDTO.setStatus(message.getStatus());
        messageDTO.setContent(message.getContent());

        // Mapping Relations
        messageDTO.setChat(message.getChat().getChatId());
        messageDTO.setSender(message.getSender().getUserId());

        return messageDTO;
    }
}
