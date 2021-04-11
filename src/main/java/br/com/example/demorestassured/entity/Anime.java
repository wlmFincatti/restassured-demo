package br.com.example.demorestassured.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "animes")
public class Anime {

    @Id
    private String id;
    private String name;
    private Integer numberOfEpisodies;
    private LocalDate releaseDate;
    private List<MainCharacters> characters;
}
