package com.AprendoSpring.aprendendoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// Spring Web MVC para rodar o Swagger
@EnableWebMvc
@SpringBootApplication
// @ActiveProfiles("dev")
public class AprendendoSpringApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AprendendoSpringApplication.class, args);
	}

}
