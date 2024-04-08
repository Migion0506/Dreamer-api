package org.beru.dreammer.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.beru.dreammer.persistence.entity.MessageEntity;
import org.beru.dreammer.service.MessageService;
import org.beru.dreammer.service.dto.MessageDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import lombok.AllArgsConstructor;

@Controller
@CrossOrigin
@AllArgsConstructor
public class MessageSocketController {
    private final MessageService service;
    private final SimpUserRegistry simpUserRegistry;
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
    @MessageMapping("/chat/connected")
    @SendTo("/topic/connected")
    public List<String> getConnectedUsers(){
        return simpUserRegistry.getUsers().stream()
        .map(simpUser -> simpUser.getName())
        .collect(Collectors.toList());
    }
    @MessageMapping("/chat/{chatId}/delete/{messageId}")
    @SendTo("/topic/delete/{chatId}")
    public String delete(@DestinationVariable String chatId, @DestinationVariable String messageId){
        service.delete(messageId);
        return messageId;
    }
}
