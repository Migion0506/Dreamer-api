package org.beru.dreammer.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Getter
@Setter
public class AuditableDreamEntity {
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_public", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isPublic;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;
}
