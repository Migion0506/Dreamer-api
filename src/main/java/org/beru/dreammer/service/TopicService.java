package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.TopicEntity;
import org.beru.dreammer.persistence.repository.TopicRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.*;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepository repository;

    public TopicEntity create(TopicEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
    public List<TopicEntity> createAll(List<TopicEntity> entities){
        try {
            return repository.saveAll(entities);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
    public void delete(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
}
