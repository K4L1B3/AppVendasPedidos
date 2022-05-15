package com.AprendoSpring.aprendendoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// Spring Web MVC para rodar o Swagger
@EnableWebMvc
@SpringBootApplication
public class AprendendoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprendendoSpringApplication.class, args);
	}

}
