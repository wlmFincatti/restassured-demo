package br.com.example.demorestassured.integration.fixtures;

import br.com.example.demorestassured.integration.DemoRestassuredApplicationIT;
import br.com.example.demorestassured.integration.constants.AnimeConstantsIT;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class BaseIT extends DemoRestassuredApplicationIT {

    public static RequestSpecification requestSpecification;

    @BeforeAll
    public static void setUp() {
        basePath = AnimeConstantsIT.PATH;
        baseURI = AnimeConstantsIT.URL;
        port = AnimeConstantsIT.PORT;

        BasicAuthScheme basic = new BasicAuthScheme();
        basic.setUserName("admin");
        basic.setPassword("admin123");

        requestSpecification = new RequestSpecBuilder()
                .setAuth(basic)
                .setContentType(ContentType.JSON)
                .build();

        enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }
}
