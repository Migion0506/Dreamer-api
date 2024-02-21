package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.audit.AuditableDreamEntity;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Entity
@Table(name = "dreams")
@Getter
@Setter
@ToString
public class DreamEntity extends AuditableDreamEntity{
    @Id
    @UuidGenerator
    private String id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "dream", fetch = FetchType.EAGER)
    private List<FavoriteEntity> favorites;
}
