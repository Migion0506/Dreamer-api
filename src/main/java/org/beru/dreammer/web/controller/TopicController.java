package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.TopicEntity;
import org.beru.dreammer.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.*;

@RestController
@RequestMapping("/topics")
@CrossOrigin
@AllArgsConstructor
public class TopicController {
    private final TopicService service;

    @PostMapping
    public ResponseEntity<TopicEntity> create(@RequestBody TopicEntity entity) {
        return ResponseEntity.ok(service.create(entity));
    }
    @PostMapping("/all")
    public ResponseEntity<List<TopicEntity>> createAll(@RequestBody List<TopicEntity> topics) {
        return ResponseEntity.ok(service.createAll(topics));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicEntity> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
