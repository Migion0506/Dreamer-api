package org.beru.dreammer.web.controller;

import org.beru.dreammer.persistence.entity.FavoriteEntity;
import org.beru.dreammer.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Boolean> deleteFavorite(@RequestParam String id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.ok(true);
    }
}
