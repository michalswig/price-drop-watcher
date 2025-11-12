package com.mike.pricedropwatcher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "serpapi")
public record SerpApiProperties(String baseUrl, String apiKey) {}
