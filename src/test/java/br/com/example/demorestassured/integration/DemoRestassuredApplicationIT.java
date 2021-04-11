package br.com.example.demorestassured.integration;

import br.com.example.demorestassured.DemoRestassuredApplication;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = DemoRestassuredApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DemoRestassuredApplicationIT {
}
