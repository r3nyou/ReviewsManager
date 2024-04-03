package com.example.scheduler.service;

import com.example.scheduler.model.eventPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class eventService {

    private final WebClient webClient;

    @Autowired
    public eventService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<eventPOJO>fetchEventById(int id) {

      return webClient.get()
              .uri("/{id}",id)
              .exchangeToMono(this::handleResponse);
    }

    private Mono<eventPOJO> handleResponse(ClientResponse response) {

        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToMono(eventPOJO.class);
        }
        else if (response.statusCode().is4xxClientError()) {
            // Handle client errors (e.g., 404 Not Found)
            return Mono.error(new Exception("4XX Error"));
        }
        else if (response.statusCode().is5xxServerError()) {
            // Handle server errors (e.g., 500 Internal Server Error)
            return Mono.error(new RuntimeException("Server error"));
        }
        else {
            // Handle other status codes as needed
            return Mono.error(new RuntimeException("Unexpected error"));
        }
    }
}