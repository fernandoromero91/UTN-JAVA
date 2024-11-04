package com.itr.reserva_baile.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Reserva de Espacios de Baile")
                        .version("1.0")
                        .description("Sistema de gestión para reservas de clases y estudios de baile")
                        .contact(new Contact()
                                .name("Fer Romero")
                                .email("fer.romero1991@gmail.com")));
    }
}