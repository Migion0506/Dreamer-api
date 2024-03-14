package org.beru.dreammer.web.controller;

import org.apache.catalina.connector.Response;
import org.beru.dreammer.persistence.entity.UserEntity;
import org.beru.dreammer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService service;
    
    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> getMethodName() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/account")
    public ResponseEntity<UserEntity> getAccount(){
        return ResponseEntity.ok(service.getAccount());
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> findOne(@PathVariable String username){
        return ResponseEntity.ok(service.findOne(username));
    }
    @GetMapping("/discover")
    public ResponseEntity<List<UserEntity>> discover() {
        return ResponseEntity.ok(service.discover());
    }
    
    @PostMapping
    public ResponseEntity<UserEntity> create(@Valid @RequestBody UserEntity entity) {
        return ResponseEntity.ok(service.save(entity));
    }
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<UserEntity>> findMembersInChat(@PathVariable String chatId) {
        return ResponseEntity.ok(service.findMembersInChat(chatId));
    }
    
}
