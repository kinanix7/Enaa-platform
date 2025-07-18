package com.enaa.apprenant.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Springdoc OpenAPI.
 * Provides custom OpenAPI documentation for the Apprenant Service.
 */
@Configuration
public class SpringdocConfig {

    /**
     * Configures and returns a custom OpenAPI bean.
     * @return The custom OpenAPI instance.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Apprenant Service API").version("1.0").description("API documentation for Apprenant Service"));
    }
}
