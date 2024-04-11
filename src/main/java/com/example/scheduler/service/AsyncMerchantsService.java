package com.example.scheduler.service;

import okhttp3.*;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class AsyncMerchantsService {

    public String callMerchantsAPI() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String baseUrl = "https://maps.googleapis.com/maps/api/place/search/json?";
        int radius = 200;
        double lot = 25.0527;
        double lon = 121.5204;
        boolean sensor = false;
        String types = "restaurant";
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("maps.googleapis.com")
                .addPathSegments("maps/api/place/search/json")
                .addQueryParameter("key", "AIzaSyCppk2hdPJk7w5jrZuLs8-4EtdU1V7NmZA")
                .addQueryParameter("location", lot + "," + lon)
                .addQueryParameter("radius", "100")
                .addQueryParameter("sensor", String.valueOf(sensor))
                .addQueryParameter("types", types);
//節省GOOGLEEMAPAPI花費_先字串代替
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
