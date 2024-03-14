package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.DreamEntity;
import org.beru.dreammer.service.DreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<DreamEntity> findOne(@PathVariable String id) {
        return ResponseEntity.ok(service.findOne(id));
    }
}
