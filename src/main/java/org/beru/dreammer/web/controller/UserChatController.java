package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.UserChatEntity;
import org.beru.dreammer.service.UserChatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users-chats")
@AllArgsConstructor
public class UserChatController {
    private final UserChatService service;

    @PostMapping
    public ResponseEntity<UserChatEntity> create(@RequestBody UserChatEntity entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(entity));
    }
    
}
