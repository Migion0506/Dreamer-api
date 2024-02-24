package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.audit.AuditableDreamEntity;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Entity
@Table(name = "dreams")
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@ToString
public class DreamEntity extends AuditableDreamEntity{
    @Id
    @UuidGenerator
    private String id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Content is mandatory")
    private String content;

    @Column(name = "picture_url")
    private String pictureUrl;

    @OneToMany(mappedBy = "dreamEntity", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<FavoriteEntity> favorites;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;

    @OneToMany(mappedBy = "dream", fetch = FetchType.EAGER)
    private List<GenreEntity> genres;
}
