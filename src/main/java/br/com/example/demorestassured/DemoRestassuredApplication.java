package br.com.example.demorestassured;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class DemoRestassuredApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestassuredApplication.class, args);
	}

}
