package br.com.example.demorestassured.integration.fixtures;

import br.com.example.demorestassured.entity.Anime;
import br.com.example.demorestassured.entity.MainCharacters;
import br.com.example.demorestassured.entity.enums.GenderEnum;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class AnimeMock {

    public static Anime gimmeAnime(String id) {
        MainCharacters ichigo = MainCharacters.builder()
                .setName("Ichigo")
                .setGender(GenderEnum.MALE)
                .setNvPower(100)
                .build();

        return Anime.builder()
                .setId(id)
                .setName("Bleach")
                .setReleaseDate(LocalDate.of(2001, Month.JANUARY, 1))
                .setNumberOfEpisodies(366)
                .setCharacters(Arrays.asList(ichigo))
                .build();
    }
}
