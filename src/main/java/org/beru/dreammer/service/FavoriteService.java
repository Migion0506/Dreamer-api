package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.FavoriteEntity;
import org.beru.dreammer.persistence.entity.id.UserDreamId;
import org.beru.dreammer.persistence.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavoriteService {
    private final FavoriteRepository repository;

    public FavoriteEntity addFavorite(FavoriteEntity favorite) {
        try {
            return repository.save(favorite);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }

    public void deleteFavorite(UserDreamId favorite) {
        try {
            repository.deleteById(favorite);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getMessage());
        }
    }
}
