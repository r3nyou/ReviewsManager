package com.example.scheduler.configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GoogleMapWebClient {
    @Value("${GAPIkey}")
    private String GAPIkey;
    String baseUrl = "https://places.googleapis.com/v1/places:searchText" ;
    //連線webClient，客製化
    @Bean
    public WebClient GoogleMapWebClient() {
        WebClient googleMapWebClient = WebClient.builder()
                .baseUrl(baseUrl)
                //.defaultHeader("Content-Type","application/json")
                //.defaultHeader("X-Goog-Api-Key",GAPIkey)
                //.defaultHeaders(this::addDefaultHeaders)
                .build();
        return googleMapWebClient;
    }
}
