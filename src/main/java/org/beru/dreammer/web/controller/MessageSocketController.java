package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.MessageEntity;
import org.beru.dreammer.service.MessageService;
import org.beru.dreammer.service.dto.MessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;

@Controller
@CrossOrigin
@AllArgsConstructor
public class MessageSocketController {
    private final MessageService service;
    @MessageMapping("/chat/{chatId}/send")
    @SendTo("/topic/messages/{chatId}")
    public MessageDto send(@DestinationVariable String chatId, MessageDto message){
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setChatId(chatId);
        messageEntity.setCreatedBy(message.getCreatedBy());
        messageEntity.setText(message.getText());
        messageEntity.setCreatedAt(message.getCreatedAt());
        return service.save(messageEntity);
    }
    @MessageMapping("/chat/{chatId}/delete")
    @SendTo("/topic/delete/{chatId}")
    public MessageDto delete(@DestinationVariable String chatId, MessageDto message){
        service.delete(message.getId());
        return message;
    }
}
