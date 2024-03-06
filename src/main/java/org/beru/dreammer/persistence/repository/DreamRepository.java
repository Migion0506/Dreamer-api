package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.DreamEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface DreamRepository extends ListCrudRepository<DreamEntity, String>{

}
