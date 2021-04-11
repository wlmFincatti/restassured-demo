package br.com.example.demorestassured.repository;

import br.com.example.demorestassured.entity.Anime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends MongoRepository<Anime, String> {
}
