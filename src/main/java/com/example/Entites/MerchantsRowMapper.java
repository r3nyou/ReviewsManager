package com.example.Entites;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MerchantsRowMapper implements RowMapper<Merchants>{

    @Override
    public Merchants mapRow(ResultSet rs, int rowNum) throws SQLException {
        Merchants merchants = new Merchants();
        merchants.setId(rs.getInt("id"));
        merchants.setPlaceId(rs.getString("placeId"));
        merchants.setDisplayName(rs.getString("displayName"));
        merchants.setUserRatingCount(rs.getInt("userRatingCount"));
        merchants.setRating(rs.getFloat("rating"));
        merchants.setGoogleMapsUri(rs.getString("googleMapsUri"));
        merchants.setWebsiteUri(rs.getString("websiteUri"));
        merchants.setCreateAt(rs.getTime("createAt"));
        merchants.setUpdateAt(rs.getTime("updateAt"));
        return merchants;
    }
}

/*
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
* */