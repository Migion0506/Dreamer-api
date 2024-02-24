package org.beru.dreammer.persistence.repository;

import org.beru.dreammer.persistence.entity.GenreEntity;
import org.beru.dreammer.persistence.entity.id.DreamGenreId;
import org.springframework.data.repository.ListCrudRepository;

public interface GenreRepository extends ListCrudRepository<GenreEntity, DreamGenreId>{

}
