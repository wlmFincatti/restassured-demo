package br.com.example.demorestassured.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        tags = "@auth and not @noauth",
        plugin = {"json:target/cucumber.json", "pretty", "html:target/cucumber-reports"})
public class CucumberBDD {
}
