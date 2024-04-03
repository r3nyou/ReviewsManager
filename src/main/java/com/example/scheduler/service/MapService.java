package com.example.scheduler.service;
import org.springframework.http.HttpHeaders;
import com.example.scheduler.configuration.GoogleMapWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;



@Service
public class MapService {
    @Value("${GAPIkey}")
    private String GAPIkey;
    private final GoogleMapWebClient googleMapWebClient;
    @Autowired
    public MapService(GoogleMapWebClient googleMapWebClient) {
        this.googleMapWebClient = googleMapWebClient;
    }

    public Mono<String>fetchPlaceBytext(String text) {
        HttpHeaders headers = new HttpHeaders();
        String jsonBody = "{\"textQuery\": \"Spicy Vegetarian Food in Sydney, Australia\"}";
        headers.add("Content-Type","application/json; charset=utf-8");
        headers.add("X-Goog-Api-Key","AIzaSyCppk2hdPJk7w5jrZuLs8-4EtdU1V7NmZA");
        headers.add("X-Goog-FieldMask","places.displayName,places.formattedAddress,places.id");
        WebClient localClient = googleMapWebClient.GoogleMapWebClient()
                .mutate()
                .baseUrl("https://places.googleapis.com/v1/places:searchText")
                //.defaultHeader("Content-Type","application/json")
                //.defaultHeader("X-Goog-Api-Key",GAPIkey)
                //.defaultHeader("X-Goog-FieldMask","places.displayName,places.formattedAddress,places.id")
                .build();

        return  localClient
                .post()
                .headers(h -> h.addAll(headers))
                //.bodyValue(new Object(){String textQuery = "Spicy Vegetarian Food in Sydney, Australia";})
                //.bodyValue(new JSONObject().put("textQuery","Spicy Vegetarian Food in Sydney, Australia"))
                .bodyValue(jsonBody)
                .exchangeToMono(this::handleResponse)
                .doOnError(error -> {
                    // 记录错误信息
                    System.err.println("Error occurred: " + error.getMessage());
                });
    }
    private Mono<String> handleResponse(ClientResponse response) {
        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToMono(String.class);
        }
        else if (response.statusCode().is4xxClientError()) {
            // Handle client errors (e.g., 404 Not Found)
         //   System.out.println("exception--> "+Mono.);
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