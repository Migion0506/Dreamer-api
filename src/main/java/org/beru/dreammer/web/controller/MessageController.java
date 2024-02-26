package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.MessageEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class MessageController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public MessageEntity send(MessageEntity messageEntity){
        return new MessageEntity(messageEntity.getFrom(), messageEntity.getText());
    }
}
