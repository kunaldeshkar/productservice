package com.dev.scaler.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// This is Configuration class, and consider it at the time of Starting application
@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
