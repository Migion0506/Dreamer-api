package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.DreamEntity;
import org.beru.dreammer.service.DreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.*;

@RestController
@RequestMapping("/dreams")
@CrossOrigin
@AllArgsConstructor
public class DreamController {
    private final DreamService service;

    @PostMapping
    public ResponseEntity<DreamEntity> save(@RequestBody DreamEntity entity) {
        return ResponseEntity.ok(service.save(entity));
    }
    @GetMapping
    public ResponseEntity<List<DreamEntity>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DreamEntity> findOne(@RequestParam String id) {
        return ResponseEntity.ok(service.findOne(id));
    }
}
