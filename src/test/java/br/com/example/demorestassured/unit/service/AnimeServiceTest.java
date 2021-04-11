package br.com.example.demorestassured.unit.service;

import br.com.example.demorestassured.entity.Anime;
import br.com.example.demorestassured.repository.AnimeRepository;
import br.com.example.demorestassured.service.AnimeService;
import br.com.example.demorestassured.service.impl.AnimeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AnimeServiceTest {

    private static final String ID_ANIME = "id";

    private Anime animeMock;
    private AnimeRepository repository;
    private AnimeService service;

    @BeforeEach
    void setUp() {
        this.repository = mock(AnimeRepository.class);
        this.service = new AnimeServiceImpl(this.repository);
        this.animeMock = mock(Anime.class);
    }

    @Test
    void findAnimes() {
        List<Anime> animesMock = Arrays.asList(animeMock);
        when(repository.findAll()).thenReturn(animesMock);

        List<Anime> result = service.findAnimes();

        verify(repository, times(1)).findAll();
        assertEquals(animesMock, result);
    }

    @Test
    void findAnime() {
        when(repository.findById(ID_ANIME)).thenReturn(Optional.of(animeMock));

        Anime result = service.findAnime(ID_ANIME);

        verify(repository, times(1)).findById(ID_ANIME);
        assertEquals(animeMock, result);
    }

    @Test
    void deleteAnimeById() {
        when(repository.findById(ID_ANIME)).thenReturn(Optional.of(animeMock));
        doNothing().when(repository).deleteById(ID_ANIME);

        service.deleteAnimeById(ID_ANIME);

        verify(repository, times(1)).findById(ID_ANIME);
        verify(repository, times(1)).deleteById(ID_ANIME);
    }

    @Test
    void createAnime() {
        when(repository.save(animeMock)).thenReturn(animeMock);

        Anime result = service.createAnime(animeMock);

        verify(repository, times(1)).save(animeMock);
        assertEquals(animeMock, result);
    }
}