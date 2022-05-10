package com.inspera.interview.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file responsible for all object mapper configurations
 */
@Configuration
public class ObjectMapperConfig {

    /**
     * Initialize base object mapper
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {

        return new ObjectMapper();
    }
}
