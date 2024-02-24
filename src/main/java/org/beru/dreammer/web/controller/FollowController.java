package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.FollowEntity;
import org.beru.dreammer.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/follow")
@CrossOrigin
@AllArgsConstructor
public class FollowController {
    private final FollowService service;

    @PostMapping
    public ResponseEntity<FollowEntity> follow(@RequestBody FollowEntity entity) {
        return ResponseEntity.ok(service.follow(entity));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Boolean> unFollow(@RequestParam String username) {
        service.unFollow(username);
        return ResponseEntity.ok(true);
    }
}
