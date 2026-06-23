package com.postulante.postulante.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Ecosistema de Gestión Laboral")
                        .version("1.0")
                        .description("Documentación unificada de servicios para la administración de Empresas, Postulantes y Hojas de Vida (CV)."));
    }
}