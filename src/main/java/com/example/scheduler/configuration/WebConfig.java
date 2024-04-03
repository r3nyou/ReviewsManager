package com.example.scheduler.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.function.Consumer;

@Configuration
public class WebConfig {

    //測試
    @Bean
    public WebClient webClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8081/jdbc/events")
                .defaultHeader("","")
                .build();
        return webClient;
    }

    //連線webClient，客製化
    public WebClient webClient(String baseUrl, Consumer<HttpHeaders> headers) {
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(headers)
                .build();
        return webClient;
    }

}
