package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.FavoriteEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface FavoriteRepository extends ListCrudRepository<FavoriteEntity, String>{

    List<FavoriteEntity> findByCreatedBy(String createBy);

}
