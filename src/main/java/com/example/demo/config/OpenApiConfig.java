package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // ✅ DO NOT CHANGE SERVER / PORT
                .servers(List.of(
                        new Server().url("https://9052.pro604cr.amypo.ai/")
                ))
                // ✅ ADD SECURITY REQUIREMENT (shows Authorize button)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                // ✅ DEFINE JWT SECURITY SCHEME
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
