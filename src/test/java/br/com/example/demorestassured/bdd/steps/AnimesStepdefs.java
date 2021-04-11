package br.com.example.demorestassured.bdd.steps;

import br.com.example.demorestassured.DemoRestassuredApplication;
import br.com.example.demorestassured.integration.fixtures.AnimeMock;
import br.com.example.demorestassured.repository.AnimeRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(classes = DemoRestassuredApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnimesStepdefs {

    private String id;
    private Response response;
    private RequestSpecification requestSpecification;
    @Autowired
    private AnimeRepository repository;

    @Before
    public void setup() {
        port = 8080;
        basePath = "/animes";
        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    @Given("i have id {string}")
    public void iHaveId(String id) {
        this.id = id;
        this.repository.save(AnimeMock.gimmeAnime(id));
    }

    @When("i make request {string} to endpoint {string}")
    public void iMakeRequestToEndpoint(String httpVerb, String path) {
        this.response = Optional.ofNullable(this.id)
                .map(id -> given(this.requestSpecification)
                        .request(httpVerb, path, this.id)
                        .thenReturn())
                .orElseGet(() -> given(this.requestSpecification)
                        .request(httpVerb, path)
                        .thenReturn());
    }

    @Then("i expected status code {int}")
    public void iExpectedStatusCode(int statusCodeExpected) {
        int statusCodeResult = this.response.getStatusCode();

        assertEquals(statusCodeExpected, statusCodeResult);
    }

    @Given("i authenticate  with user {string} and password {string}")
    public void iAuthanticateWithUserAndPassword(String user, String password) {
        BasicAuthScheme basic = new BasicAuthScheme();
        basic.setUserName(user);
        basic.setPassword(password);

        this.requestSpecification = new RequestSpecBuilder()
                .setAuth(basic)
                .build();
    }
}
