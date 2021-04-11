package br.com.example.demorestassured.service.impl;

import br.com.example.demorestassured.entity.Anime;
import br.com.example.demorestassured.repository.AnimeRepository;
import br.com.example.demorestassured.service.AnimeService;
import br.com.example.demorestassured.service.exception.AnimeNotFounException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AnimeServiceImpl implements AnimeService {

    private AnimeRepository repository;

    public AnimeServiceImpl(AnimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Anime> findAnimes() {
        log.info("find animes");
        return this.repository.findAll();
    }

    @Override
    public Anime findAnime(String id) {
        log.info("find anime with id {}", id);
        return this.repository.findById(id).orElseThrow(() -> new AnimeNotFounException(id));
    }

    @Override
    public void deleteAnimeById(String id) {
        log.info("delete anime with id {}", id);
        if (this.repository.findById(id).isPresent()) {
            this.repository.deleteById(id);
        } else {
            throw new AnimeNotFounException(id);
        }
    }

    @Override
    public Anime createAnime(Anime anime) {
        log.info("creating anime [{}]", anime.toString());
        return this.repository.save(anime);
    }

}
