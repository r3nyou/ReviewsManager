package com.example.scheduler.service;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.xml.validation.Schema;
import java.io.IOException;
@Service
public class CustomReviewsService {
    public String callPlaceDetailsAPI(String placeId) throws IOException {
    OkHttpClient client = new OkHttpClient();
    //String id = "ChIJj61dQgK6j4AR4GeTYWZsKWw";
        String id = placeId;
        HttpUrl.Builder httpUrl = new HttpUrl.Builder()
            .scheme("https")
            .host("places.googleapis.com")
            .addPathSegments("v1/places")
            .addPathSegments(id);
    Request request = new Request.Builder()
            .addHeader("Content-Type","application/json")
            .addHeader("X-Goog-Api-Key","AIzaSyCppk2hdPJk7w5jrZuLs8-4EtdU1V7NmZA")
            .addHeader("X-Goog-FieldMask","id,websiteUri,googleMapsUri,reviews")
            .url(httpUrl.build())
            .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String res = response.body().string();
            System.out.println("地點詳細API回傳 ==>  "+res);
            return res;
        }
    }
}