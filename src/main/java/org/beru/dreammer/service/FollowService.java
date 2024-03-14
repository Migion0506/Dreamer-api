package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.FollowEntity;
import org.beru.dreammer.persistence.repository.FollowRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    public FollowEntity follow(FollowEntity entity) {
        try{
            return followRepository.save(entity);
        }catch(Exception e){
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
    @Transactional
    public Long unFollow(String username) {
        try{
            return followRepository.deleteByFollowing(username);
        }catch(Exception e){
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
}
