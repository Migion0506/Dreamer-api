package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.ChatEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ChatRepository extends ListCrudRepository<ChatEntity, String>{

}
