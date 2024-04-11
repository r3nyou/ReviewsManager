package com.example.Entites;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetails {
    @JsonProperty("id")
    String id ;
    @JsonProperty("googleMapsUri")
    String googleMapsUri;
    @JsonProperty("websiteUri")
    String websiteUri;

    public String getId() {
        return id;
    }

    public String getGoogleMapsUri() {
        return googleMapsUri;
    }

    public String getWebsiteUri() {
        return websiteUri;
    }

    public Review[] getReviews() {
        return reviews;
    }

    @JsonProperty("reviews")
    Review[] reviews;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Review{
        @JsonProperty("rating")
        int rating;

        public int getRating() {
            return rating;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public jsonText getText() {
            return text;
        }

        @JsonProperty("publishTime")
        String publishTime;
        @JsonProperty("text")
        jsonText text;

        public static class jsonText{
            @JsonProperty("text")
            String text;

            public String getText() {
                return text;
            }

            @JsonProperty("languageCode")
            String languageCode;
            public void printAllProps(){
                System.out.println("text="+text);
                System.out.println("languageCode"+languageCode);
            }
        }
        public void printAllProps(){
            System.out.println("rating"+rating);
            System.out.println("publishTime"+publishTime);
            text.printAllProps();
        }
    }
    public void printAllProps(){
        System.out.println("id="+id);
        System.out.println("googleMapsUri="+googleMapsUri);
        System.out.println("websiteUri="+websiteUri);
    }
}