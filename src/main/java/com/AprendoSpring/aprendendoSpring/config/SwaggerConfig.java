package com.AprendoSpring.aprendendoSpring.config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.RequestHandledEvent;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket docket(){
         return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors
                .basePackage("com.AprendoSpring.aprendendoSpring.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo((apiInfo()));  
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("API de vendas")
            .description("API para o projeto de vendas")
            .version("1.0")
            .contact(contact())
            .build();
    }

    private Contact contact (){
        return new Contact
            ("Luiz", 
            "https://github.com/K4L1B3", 
            "luizhlimagomes28@gmail.com");
    }

}