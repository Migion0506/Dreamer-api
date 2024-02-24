package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.FavoriteEntity;
import org.beru.dreammer.persistence.entity.UserEntity;
import org.beru.dreammer.persistence.entity.id.UserDreamId;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface FavoriteRepository extends ListCrudRepository<FavoriteEntity, UserDreamId>{

    List<FavoriteEntity> findByCreatedBy(String createBy);

}
