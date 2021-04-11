package br.com.example.demorestassured.controller.exception;

import br.com.example.demorestassured.service.exception.AnimeNotFounException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnimeException {

    @ExceptionHandler({AnimeNotFounException.class})
    public ResponseEntity exceptionHandler(RuntimeException exception) {
        return ResponseEntity.notFound().build();
    }
}
