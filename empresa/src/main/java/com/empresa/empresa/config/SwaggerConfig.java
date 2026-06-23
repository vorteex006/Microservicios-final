package com.empresa.empresa.config;

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
                        .title("API Microservicio Empresas")
                        .version("1.0")
                        .description("Documentación detallada del microservicio de Empresas para el Ecosistema de Gestión Laboral. Gestiona el registro y administración de datos corporativos."));
    }
}