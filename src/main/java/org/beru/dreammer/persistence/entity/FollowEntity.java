package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.audit.AuditableDreamEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EntityListeners({AuditingEntityListener.class})
@Table(name = "follows")
@ToString
@Entity
@Getter
@Setter
public class FollowEntity extends AuditableDreamEntity{
    @Id
    private String following;

    @ManyToOne
    @JoinColumn(name = "following", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity followers;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity followings;
}
