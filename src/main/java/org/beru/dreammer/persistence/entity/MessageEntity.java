package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.audit.AuditableMessageEntity;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class MessageEntity extends AuditableMessageEntity{
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "follow_id", nullable = false, updatable = false)
    private String followId;

    private String text;

    @ManyToOne
    @JoinColumn(name = "follow_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private FollowEntity follow;
}
