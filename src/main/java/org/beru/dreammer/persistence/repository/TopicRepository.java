package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.TopicEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface TopicRepository extends ListCrudRepository<TopicEntity, String>{

}
