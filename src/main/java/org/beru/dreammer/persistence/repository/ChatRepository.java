package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.ChatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface ChatRepository extends ListCrudRepository<ChatEntity, String>{
    @Query(value = "SELECT c.* FROM chats c JOIN users_chats uc1 ON c.id = uc1.chat_id JOIN users_chats uc2 ON c.id = uc2.chat_id WHERE uc1.username = ?1 AND uc2.username = ?2",nativeQuery = true)
    ChatEntity checkIfExists(String username, String otherUser);
}
