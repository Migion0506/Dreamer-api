package org.beru.dreammer.persistence.audit;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.*;

@MappedSuperclass
@Getter
@Setter
public class AuditableUserEntity {
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(name = "is_locked", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isLocked;
    @Column(name = "is_disabled", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isDisabled;
}
