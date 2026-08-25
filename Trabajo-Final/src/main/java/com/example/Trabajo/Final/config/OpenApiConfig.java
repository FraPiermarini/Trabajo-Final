package com.example.Trabajo.Final.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI trabajofinalOpenAPI(){
        return new OpenAPI()
           .info(new Info()
                .title("API - Trabajo Final")
                .description("API REST para gestion de estadisticas, jugadores, partidos, categorias e incidencias ")
                .version("1.0"))
            .addSecurityItem(
                new SecurityRequirement()
                    .addList("bearerAuth")
            )
            .components(
                new io.swagger.v3.oas.models.Components()
                    .addSecuritySchemes(
                        "bearerAuth",
                        new SecurityScheme()
                            .name("Authorization")
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT"))
            );
    }
}
