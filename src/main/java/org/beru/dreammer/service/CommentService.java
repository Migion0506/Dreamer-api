package org.beru.dreammer.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.beru.dreammer.persistence.entity.CommentEntity;
import org.beru.dreammer.persistence.repository.CommentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository repository;

    public CommentEntity addComment(CommentEntity comment) {
        try {
            return repository.save(comment);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error adding comment: " + e.getMessage());
        }
    }

    public CommentEntity editComment(CommentEntity comment, String id) {
        try {
            CommentEntity existingComment = repository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
            existingComment.setComment(comment.getComment());
            return repository.save(existingComment);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error editing comment: " + e.getMessage());
        }
    }

    public void deleteComment(String id) {
        try {
            repository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting comment: " + e.getMessage());
        }
    }
}
