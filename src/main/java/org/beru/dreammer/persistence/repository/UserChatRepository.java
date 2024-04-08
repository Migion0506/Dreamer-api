package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.UserChatEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UserChatRepository extends ListCrudRepository<UserChatEntity, String> {
}
