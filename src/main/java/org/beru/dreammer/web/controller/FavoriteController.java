package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.FavoriteEntity;
import org.beru.dreammer.persistence.entity.id.UserDreamId;
import org.beru.dreammer.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFavorite(@RequestParam UserDreamId id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.ok(true);
    }
}
