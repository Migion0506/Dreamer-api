package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.ChatEntity;
import org.beru.dreammer.persistence.entity.UserEntity;
import org.beru.dreammer.persistence.repository.ChatRepository;
import org.beru.dreammer.persistence.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatEntity save(String userSecondary) {
        try{
            String userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            UserEntity user1 = userRepository.findById(userPrincipal).get();
            UserEntity user2 = userRepository.findById(userSecondary).get();
            ChatEntity entity = new ChatEntity();
            entity.getUsers().add(user1);
            entity.getUsers().add(user2);
            return chatRepository.save(entity);
        }catch(Exception e){
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
    public List<ChatEntity> findAll(){
        try {
            return chatRepository.findAll();
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
    public ChatEntity findOne(String id){
        try {
            return chatRepository.findById(id).orElseThrow(() -> new RestRequestEntityExceptionHandler("This chat doesn't exist."));
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
    public void delete(String id){
        try {
            chatRepository.deleteById(id);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
}
