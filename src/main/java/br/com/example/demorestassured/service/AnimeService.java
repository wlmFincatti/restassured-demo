package br.com.example.demorestassured.service;

import br.com.example.demorestassured.entity.Anime;
import br.com.example.demorestassured.repository.AnimeRepository;
import br.com.example.demorestassured.service.exception.AnimeNotFounException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {

    private AnimeRepository repository;

    public AnimeService(AnimeRepository repository) {
        this.repository = repository;
    }

    public List<Anime> findAnimes() {
        return this.repository.findAll();
    }

    public Anime findAnime(String id) {
        return this.repository.findById(id).orElseThrow(() -> new AnimeNotFounException(id));
    }

    public void deleteAnimeById(String id) {
        if (this.repository.findById(id).isPresent()) {
            this.repository.deleteById(id);
        } else {
            throw new AnimeNotFounException(id);
        }
    }

    public Anime createAnime(Anime anime) {
        return this.repository.save(anime);
    }

}
