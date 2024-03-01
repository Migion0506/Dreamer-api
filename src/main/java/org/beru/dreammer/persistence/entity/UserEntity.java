package org.beru.dreammer.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.*;
import java.util.*;

import org.beru.dreammer.persistence.audit.AuditUserListener;
import org.beru.dreammer.persistence.audit.AuditableUserEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
@EntityListeners({AuditingEntityListener.class, AuditUserListener.class})
public class UserEntity extends AuditableUserEntity{
    @Id
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @NotBlank(message = "Email is required")
    @Column(nullable = false)
    private String email;
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;
    
    @Column(columnDefinition = "Date")
    private LocalDateTime birthday;
    @Column(name = "is_dreamer", columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean isDreamer;
    private String gender;
    
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<ChatEntity> chats = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<DreamEntity> dreams;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<FavoriteEntity> favorites;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "followers", fetch = FetchType.EAGER)
    private List<FollowEntity> followers;
    @OneToMany(mappedBy = "followings", fetch = FetchType.EAGER)
    private List<FollowEntity> followings;
}
