package com.example.Entites;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place{
@JsonProperty("Place")
    PlaceDetails[] Place;

    public PlaceDetails[] getPlace() {
        return Place;
    }

    public void setPlace(PlaceDetails[] place) {
        Place = place;
    }

    public class PlaceDetails{
        @JsonProperty("id")
        String id;
        @JsonProperty("formattedAddress")
        String formattedAddress;
        @JsonProperty("displayName")
        displayName[] displayName;
    }
    public class displayName{
        @JsonProperty("text")
        String text;
        @JsonProperty("languageCode")
        String languageCode;
    }
}

