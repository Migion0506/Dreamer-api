package org.beru.dreammer.persistence.entity;

import jakarta.persistence.*;
import org.beru.dreammer.persistence.audit.AuditableDreamEntity;
import org.beru.dreammer.persistence.entity.id.UserDreamId;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@ToString
@EntityListeners({AuditingEntityListener.class})
public class FavoriteEntity extends AuditableDreamEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false, length = 50)
    private String dream;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "dream", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private DreamEntity dreamEntity;
}
