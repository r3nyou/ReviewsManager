package com.example.Entites;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Merchants {
    int id;
    String placeId;
    String displayName;

    float rating ;
    int userRatingCount;
    String googleMapsUri;
    String websiteUri;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getUserRatingCount() {
        return userRatingCount;
    }

    public void setUserRatingCount(int userRatingCount) {
        this.userRatingCount = userRatingCount;
    }

    public String getGoogleMapsUri() {
        return googleMapsUri;
    }

    public void setGoogleMapsUri(String googleMapsUri) {
        this.googleMapsUri = googleMapsUri;
    }

    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public  String getAllProps(){
        return "Merchant{" +
                "id=" + id +
                "placeId=" + placeId +
                "rating=" + rating +
                "displayName=" + displayName +
                "userRatingCount=" + userRatingCount +
                "googleMapsUri=" + googleMapsUri +
                "websiteUri" + websiteUri +
                "createAt=" + createAt +
                "updateAt=" + updateAt +
                '}';
    }
}
/*



* "Event{" +
                "id=" + id +
                ",name=" + name +
                ",triggerTime=" + triggerTime +
                ",createAt=" + createAt +
                '}';
* */