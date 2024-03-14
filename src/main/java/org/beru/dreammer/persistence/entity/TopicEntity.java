package org.beru.dreammer.persistence.entity;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "topics")
@Getter
@Setter
@ToString
public class TopicEntity {
    @Id
    @UuidGenerator
    private String id;
    private String topic;
    @Column(name = "dream_id")
    private String dreamId;

    @ManyToOne
    @JoinColumn(name = "dream_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private DreamEntity dream;
}
