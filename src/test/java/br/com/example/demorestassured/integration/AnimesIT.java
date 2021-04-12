package br.com.example.demorestassured.integration;

import br.com.example.demorestassured.entity.Anime;
import br.com.example.demorestassured.entity.enums.GenderEnum;
import br.com.example.demorestassured.integration.constants.AnimeConstantsIT;
import br.com.example.demorestassured.integration.fixtures.AnimeMock;
import br.com.example.demorestassured.integration.fixtures.BaseIT;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnimesIT extends BaseIT {

    @Test
    void naoDeveAcessarApiAnimesSemAutenticao() {
        given()
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .body("error", is("Unauthorized"))
                .body("path", is("/animes"));
    }

    @Order(1)
    @Test
    void deveBuscarAnimes() {
        given(BaseIT.requestSpecification)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$.size()", is(1))
                .body("[0].name", is("Bleach"))
                .body("[0].characters.size()", is(1))
                .body("[0].characters[0].name", is("Ichigo"))
                .body("[0].characters[0].gender", equalTo(GenderEnum.MALE.toString()))
                .body("[0].characters[0].nvPower", is(100));
    }

    @Test
    void deveBuscarAnimePeloId() {
        given(BaseIT.requestSpecification)
                .pathParam("id", AnimeConstantsIT.ID_ANIME)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", is(AnimeConstantsIT.ID_ANIME))
                .body("name", equalToIgnoringCase("bleach"))
                .body("numberOfEpisodies", is(366))
                .body("releaseDate", equalTo("2001-01-01"))
                .rootPath("characters")
                .body("[0].name", is("Ichigo"))
                .body("[0].gender", equalTo(GenderEnum.MALE.toString()))
                .body("[0].nvPower", is(100));
    }

    @Test
    void naoDeveEncontrarAnimePeloIdERetornarStatusCodeNotFound() {
        given(BaseIT.requestSpecification)
                .pathParam("id", "id_invalid")
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deveCriarNovoAnime() {
        Anime anime = AnimeMock.gimmeAnime(AnimeConstantsIT.ID_ANIME_NOVO);

        given(BaseIT.requestSpecification)
                .body(anime)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", String.format("%s:%s%s/%s", AnimeConstantsIT.URL, AnimeConstantsIT.PORT, AnimeConstantsIT.PATH, AnimeConstantsIT.ID_ANIME_NOVO))
                .body(matchesJsonSchemaInClasspath("anime.json"));
    }

    @Test
    void deveDeletarAnimePeloId() {
        given(BaseIT.requestSpecification)
                .pathParam("id", AnimeConstantsIT.ID_ANIME)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
