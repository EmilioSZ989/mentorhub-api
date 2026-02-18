package com.emi.mentorhub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(apiInfo())
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor local"),
                        new Server()
                                .url("https://mentorhub-api.onrender.com")
                                .description("Servidor producción (Render)")
                ))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, bearerScheme()));
    }

    private Info apiInfo() {
        return new Info()
                .title("MentorHub API")
                .description("""
                        API REST profesional para gestión de mentorías técnicas.
                        
                        Funcionalidades principales:
                        - Registro y autenticación JWT
                        - Roles: ADMIN, MENTOR, STUDENT
                        - Solicitudes de mentoría
                        - Aprobación / rechazo
                        - Métricas administrativas
                        - Seguridad por roles
                        
                        Proyecto desarrollado con:
                        Spring Boot 3, Spring Security, JWT, PostgreSQL
                        """)
                .version("1.0.0")
                .contact(new Contact()
                        .name("EmiSZ - Backend Developer")
                        .email("emiliosz1708@email.com")
                        .url("https://github.com/EmilioSZ989"));
    }

    private SecurityScheme bearerScheme() {
        return new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Ingrese el token JWT obtenido en /api/auth/login");
    }
}
