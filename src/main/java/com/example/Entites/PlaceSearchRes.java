package com.example.Entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceSearchRes {
    @JsonProperty("next_page_token")
    String next_page_token ;
    @JsonProperty("status")
    String status ;
    //內嵌物件
    @JsonProperty("results")
    MerchantResult[] results ;
    public MerchantResult[] getResults() {
        return results;
    }
    public void printMerchantsProps() {
        System.out.println("next_page_token=>"+next_page_token);
        System.out.println("status=>"+status);
        System.out.println("results=>"+results);
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MerchantResult{
        @JsonProperty("name")
        String name ;

        public String getName() {
            return name;
        }

        public String getPlace_id() {
            return place_id;
        }

        public float getRating() {
            return rating;
        }

        public int getUser_ratings_total() {
            return user_ratings_total;
        }

        @JsonProperty("place_id")
        String place_id;
        @JsonProperty("rating")
        float rating;
        @JsonProperty("user_ratings_total")
        int user_ratings_total;
        public void printMerchantsProps() {
            System.out.println("name=>"+name);
            System.out.println("place_id=>"+place_id);
            System.out.println("rating=>"+rating);
            System.out.println("user_ratings_total=>"+user_ratings_total);
        }
    }
}
