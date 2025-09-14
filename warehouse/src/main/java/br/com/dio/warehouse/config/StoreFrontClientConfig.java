package br.com.dio.warehouse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class StoreFrontClientConfig {
    @Bean
    RestClient storeFrontClient(@Value("${storefront.base-path}") final String basePath){
        return RestClient.create(basePath);
    }
}
