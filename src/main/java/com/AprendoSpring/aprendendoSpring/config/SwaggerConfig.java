package com.AprendoSpring.aprendendoSpring.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Spring Web MVC para rodar o Swagger
@EnableWebMvc
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    // Path para acessar o Swagger:
    // http://localhost:8080/swagger-ui/index.html#/

    @Bean
    public Docket docket(){
         return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors
                .basePackage("com.AprendoSpring.aprendendoSpring.rest.controllers"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
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



    public ApiKey apiKey(){
        //Passar um header authorization atrásves de um header
        return new ApiKey("JWT", "Authorization", "header");
    }

  
    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "AcessEveryThing");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = authorizationScope;
        SecurityReference reference = new SecurityReference("JWT",scopes);
        List<SecurityReference> auths = new ArrayList<>();
        auths.add(reference);
        return auths; 
    }
}
