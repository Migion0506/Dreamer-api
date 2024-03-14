package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.CommentEntity;
import org.beru.dreammer.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/comments")
@CrossOrigin
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentEntity> addComment(@RequestBody CommentEntity entity) {
        CommentEntity addedComment = commentService.addComment(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentEntity> editComment(@PathVariable String id, @RequestBody CommentEntity entity) {
        CommentEntity editedComment = commentService.editComment(entity, id);
        return ResponseEntity.ok(editedComment);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CommentEntity> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
