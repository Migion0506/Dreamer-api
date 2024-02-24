package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.UserEntity;
import org.beru.dreammer.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

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
    @PostMapping
    public ResponseEntity<UserEntity> create(@Valid @RequestBody UserEntity entity) {
        return ResponseEntity.ok(service.save(entity));
    }
}
