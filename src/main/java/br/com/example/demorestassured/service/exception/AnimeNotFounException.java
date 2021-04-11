package br.com.example.demorestassured.service.exception;

public class AnimeNotFounException extends RuntimeException {
    public AnimeNotFounException(String id) {
        super(String.format("Anime not found with id %s", id));
    }
}
