package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.MessageEntity;
import org.beru.dreammer.persistence.repository.MessageRepository;
import org.beru.dreammer.service.dto.MessageDto;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageDto save(MessageEntity message) {
        try {
            System.out.println("From service: "+message);
            MessageEntity entity = messageRepository.save(message);
            return new MessageDto(entity.getId(),entity.getCreatedBy(), entity.getText(), entity.getCreatedAt());
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
    public MessageDto update(MessageEntity message) {
        try {
            MessageEntity entity = messageRepository.save(message);
            return new MessageDto(entity.getId(),entity.getCreatedBy(), entity.getText(), entity.getCreatedAt());
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
    public void delete(String id){
        try {
            messageRepository.deleteById(id);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
}
