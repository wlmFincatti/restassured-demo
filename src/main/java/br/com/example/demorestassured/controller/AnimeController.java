package br.com.example.demorestassured.controller;

import br.com.example.demorestassured.entity.Anime;
import br.com.example.demorestassured.unit.service.AnimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("animes")
public class AnimeController {

    private AnimeService service;

    public AnimeController(AnimeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Anime>> getAnimes() {
        return ResponseEntity.ok(this.service.findAnimes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable String id) {
        return ResponseEntity.ok(this.service.findAnime(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimeById(@PathVariable String id) {
        this.service.deleteAnimeById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Anime> createAnimeById(@RequestBody Anime anime) {
        Anime animeSaved = this.service.createAnime(anime);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(animeSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(anime);
    }
}
