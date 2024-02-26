package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.audit.AuditableDreamEntity;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@EntityListeners({AuditingEntityListener.class})
@Table(name = "follows")
@ToString
@Entity
@Getter
@Setter
public class FollowEntity extends AuditableDreamEntity{
    @Id
    private String following;

    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "following", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity followers;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity followings;

    @OneToMany(mappedBy = "follow", fetch = FetchType.EAGER)
    private List<MessageEntity> messages;
}
