package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.FavoriteEntity;
import org.beru.dreammer.persistence.entity.id.UserDreamId;
import org.springframework.data.repository.ListCrudRepository;

public interface FavoriteRepository extends ListCrudRepository<FavoriteEntity, UserDreamId>{

}
