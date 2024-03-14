package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.*;

public interface UserRepository extends ListCrudRepository<UserEntity, String>{
    UserEntity findByEmail(String email);
    @Query(value = "SELECT u.*, uc.chat_id FROM users_chats uc LEFT JOIN users u ON uc.username = u.username WHERE uc.chat_id = ?1", nativeQuery = true)
    List<UserEntity> findMembersInChat(String chatId);
    List<UserEntity> findAllByOrderByCreatedAtDesc();
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
