package com.example.scheduler.service;
import okhttp3.*;

import java.io.IOException;




    public class OKHttpTest {
        public static void main(String[] args) {
            // 创建OkHttpClient实例
            OkHttpClient client = new OkHttpClient();

            // 创建JSON请求体
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            String jsonBody = "{\"textQuery\": \"Spicy Vegetarian Food in Sydney, Australia\"}";
            RequestBody requestBody = RequestBody.create(jsonBody, JSON);

            // 创建POST请求，并添加自定义标头
            Request request = new Request.Builder()
                    .url("https://places.googleapis.com/v1/places:searchText")
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-Goog-Api-Key", "AIzaSyCppk2hdPJk7w5jrZuLs8-4EtdU1V7NmZA")
                    .addHeader("X-Goog-FieldMask", "places.displayName,places.formattedAddress,places.id")
                    .build();

            // 发送请求并处理响应
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    // 获取响应数据
                    String responseData = response.body().string();
                    System.out.println("Response: " + responseData);
                } else {
                    System.out.println("Request failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



