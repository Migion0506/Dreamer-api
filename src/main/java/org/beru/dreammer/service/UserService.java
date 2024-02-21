package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.UserEntity;
import org.beru.dreammer.persistence.repository.StorageRepository;
import org.beru.dreammer.persistence.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder pe;
    private final StorageRepository storageRepository;

    private Optional<UserEntity> getByUsername(String username, String email){
        return repository.findByUsernameOrEmail(username, email);
    }
    public UserEntity save(UserEntity user){
        try {
            if(getByUsername(user.getUsername(), user.getEmail()).isPresent()){
                throw new RestRequestEntityExceptionHandler("Username or email already exists");
            }
            user.setPassword(pe.encode(user.getPassword()));
            UserEntity entity = repository.save(user);
            storageRepository.deleteAll();
            storageRepository.init(entity.getUsername());
            return entity;
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getLocalizedMessage());
        }
    }
    @SuppressWarnings("null")
    public UserEntity getAccount(){
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return repository.findById(username).orElseThrow(() -> new RestRequestEntityExceptionHandler("User not found with specified id or Token invalid"));
    }
    public Iterable<UserEntity> findAll(){
        return repository.findAll();
    }
}
