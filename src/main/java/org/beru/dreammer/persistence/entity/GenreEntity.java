package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.entity.id.DreamGenreId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
@IdClass(DreamGenreId.class)
public class GenreEntity {
    @Id
    @Column(name = "genre_id", nullable = false)
    private String genre;
    @Id
    @Column(name = "dream_id", nullable = false)
    private String dreamId;

    @ManyToOne
    @JoinColumn(name = "dream_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private DreamEntity dream;
}
