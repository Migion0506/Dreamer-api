package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.FavoriteEntity;
import org.beru.dreammer.persistence.entity.UserEntity;
import org.beru.dreammer.persistence.entity.id.UserDreamId;
import org.beru.dreammer.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/favorite")
@CrossOrigin
@AllArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<FavoriteEntity> addFavorite(@RequestBody FavoriteEntity entity) {
        return ResponseEntity.ok(favoriteService.addFavorite(entity));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteEntity>> getFavorites( ) {
        return ResponseEntity.ok(favoriteService.getFavorites());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFavorite(@RequestParam UserDreamId id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.ok(true);
    }
}
