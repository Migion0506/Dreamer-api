package org.beru.dreammer.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_chats")
public class UserChatEntity {
    @Column(name = "chat_id", nullable = false)
    private String chatId;
    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany
    @JoinColumn(name = "chat_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private ChatEntity chat;
    @OneToMany
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;
}
