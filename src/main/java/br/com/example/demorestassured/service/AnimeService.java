package br.com.example.demorestassured.service;

import br.com.example.demorestassured.entity.Anime;

import java.util.List;

public interface AnimeService {

    List<Anime> findAnimes();

    Anime findAnime(String id);

    void deleteAnimeById(String id);

    Anime createAnime(Anime anime);

}
