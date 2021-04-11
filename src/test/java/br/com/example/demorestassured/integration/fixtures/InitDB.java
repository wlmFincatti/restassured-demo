package br.com.example.demorestassured.integration.fixtures;

import br.com.example.demorestassured.integration.constants.AnimeConstantsIT;
import br.com.example.demorestassured.repository.AnimeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {

    private AnimeRepository repository;

    public InitDB(AnimeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.createAnimes();
    }

    private void createAnimes() {
        this.repository.save(AnimeMock.gimmeAnime(AnimeConstantsIT.ID_ANIME));
    }
}
