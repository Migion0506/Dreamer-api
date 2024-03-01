package org.beru.dreammer.persistence.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.*;

@Getter
@Setter
@MappedSuperclass
@ToString
public class AuditableMessageEntity {
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
    @Column(name = "updated_at")
    @LastModifiedBy
    private LocalDateTime updatedAt;
}
