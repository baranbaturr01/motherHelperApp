package com.baranbatur.newMotherHelper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${base-url}")
    private String baseUrl;

    @Bean
    public String getBaseUrl() {
        return baseUrl;
    }

}
