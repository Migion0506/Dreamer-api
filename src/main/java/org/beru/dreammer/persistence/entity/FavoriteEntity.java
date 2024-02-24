package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.audit.AuditableDreamEntity;
import org.beru.dreammer.persistence.entity.id.UserDreamId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @Column(nullable = false, length = 50)
    private String dream;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "dream", referencedColumnName = "id", insertable = false, updatable = false)
    private DreamEntity dreamEntity;
}
