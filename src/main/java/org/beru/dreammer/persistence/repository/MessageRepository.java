package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.MessageEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface MessageRepository extends ListCrudRepository<MessageEntity, String>{
}
