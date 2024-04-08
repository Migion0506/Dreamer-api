package org.beru.dreammer.service;

import org.beru.dreammer.persistence.entity.UserChatEntity;
import org.beru.dreammer.persistence.repository.UserChatRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserChatService {
    private final UserChatRepository repository;

    public UserChatEntity create(UserChatEntity entity) {
        return repository.save(entity);
    }
    
}
