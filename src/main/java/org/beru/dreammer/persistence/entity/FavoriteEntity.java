package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.entity.id.UserDreamId;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
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
@IdClass(UserDreamId.class)
public class FavoriteEntity {
    @Id
    @UuidGenerator
    private String id;
    private String username;
    private String dream;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "dream", referencedColumnName = "id", insertable = false, updatable = false)
    private DreamEntity dreamEntity;
}
