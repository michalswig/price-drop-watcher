package com.mike.pricedropwatcher.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(SerpApiProperties.class)
public class SerpApiConfig {

  @Bean
  RestClient serpRestClient(SerpApiProperties properties) {
    return RestClient.builder().baseUrl(properties.baseUrl()).build();
  }
}
