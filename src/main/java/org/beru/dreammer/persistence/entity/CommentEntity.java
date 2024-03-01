package org.beru.dreammer.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.beru.dreammer.persistence.audit.AuditableDreamEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@EntityListeners({AuditingEntityListener.class})
public class CommentEntity extends AuditableDreamEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, length = 50)
    private String dream;

    @NotBlank(message = "Comment content is mandatory")
    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "dream", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private DreamEntity dreamEntity;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;

}
