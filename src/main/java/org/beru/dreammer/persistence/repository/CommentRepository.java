package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    List<CommentEntity> findByCreatedBy(String createdBy);
}
