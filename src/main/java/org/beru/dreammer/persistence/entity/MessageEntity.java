package org.beru.dreammer.persistence.entity;

import org.beru.dreammer.persistence.audit.AuditableMessageEntity;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@Entity
@Table(name = "messages")
public class MessageEntity extends AuditableMessageEntity{
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "chat_id", nullable = false)
    private String chatId;

    private String text;

    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private ChatEntity chat;
}
