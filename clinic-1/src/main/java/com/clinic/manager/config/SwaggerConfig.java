package com.clinic.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Configuration for Swagger.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * Build {@ApiKey} for auth.
     * @return
     */
    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    /**
     * Build {@ApiInfo} for our application
     * @return The {@ApiInfo} for our application.
     */
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Spring Boot Blog REST APIs",
                "Spring Boot Blog REST API Documentation",
                "1",
                "Terms of service",
                new Contact("Runsystem", "https://runsystem.net/vi/", "info@runsystem.net"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }

    /**
     * Configure a {@link springfox.documentation.spring.web.plugins.Docket} bean.
     * @return The configured {@link springfox.documentation.spring.web.plugins.Docket} bean.
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Build a {@link springfox.documentation.spi.service.contexts.SecurityContext}.
     * @return A {@link springfox.documentation.spi.service.contexts.SecurityContext}. 
     */
    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    /**
     * Build default {@SecurityReference}s for JWT authentication.
     * @return A {@list} of {@SecurityReference}.
     */
    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}
