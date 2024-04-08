package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.ChatEntity;
import org.beru.dreammer.service.ChatService;
import org.beru.dreammer.service.dto.ChatDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chats")
@CrossOrigin
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SimpUserRegistry simpUserRegistry;

    @PostMapping
    public ResponseEntity<ChatEntity> save(@RequestBody ChatDto entity) {        
        return ResponseEntity.ok(chatService.save(entity.getSecondaryUser()));
    }
    @GetMapping
    public ResponseEntity<List<ChatEntity>> findAll() {
        return ResponseEntity.ok(chatService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ChatEntity> findOne(@PathVariable String id) {
        return ResponseEntity.ok(chatService.findOne(id));
    }
    @GetMapping("/online")
    public List<String> getConnectedUsers(){
        return simpUserRegistry.getUsers().stream()
        .map(simpUser -> simpUser.getName())
        .collect(Collectors.toList());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        chatService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
