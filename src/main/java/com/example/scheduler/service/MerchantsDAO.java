package com.example.scheduler.service;

import com.example.Entites.Merchants;
import com.example.Entites.MerchantsRowMapper;
import com.example.Entites.PlaceSearchRes;
import com.example.Entites.PlaceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MerchantsDAO {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    //每日新增，新增自動忽略重複PlaceId資料
    public void insert(PlaceSearchRes placeSearchRes) {
        String sql = "INSERT IGNORE merchants(placeId, displayName, rating,userRatingCount,googleMapsUri,createAt,updateAt) " +
                "VALUES (:placeId, :displayName, :rating,:userRatingCount,:googleMapsUri,:createAt,:updateAt)";
        for(int i = 0; i< placeSearchRes.getResults().length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("placeId", placeSearchRes.getResults()[i].getPlace_id());
            map.put("displayName", placeSearchRes.getResults()[i].getName());
            map.put("rating", placeSearchRes.getResults()[i].getRating());
            map.put("userRatingCount", placeSearchRes.getResults()[i].getUser_ratings_total());
            map.put("googleMapsUri", "");
            map.put("createAt", new Timestamp(System.currentTimeMillis()));
            map.put("updateAt", new Timestamp(System.currentTimeMillis()));
            System.out.println("Insert merchants : => " + placeSearchRes.getResults()[i].getPlace_id());
            namedParameterJdbcTemplate.update(sql, map);
        }
    }
    //查詢客戶評論排程會回壓Uri。
    public void update(PlaceDetails placeDetails) {
        String sql = "UPDATE merchants SET googleMapsUri = :googleMapsUri , websiteUri = :websiteUri" +
                " WHERE placeId = :id ";
        Map<String, Object> map = new HashMap<>();
        System.out.println("googleMapsUri"+placeDetails.getGoogleMapsUri());
        System.out.println("WebsiteUri"+placeDetails.getWebsiteUri());
        System.out.println("id"+placeDetails.getId());

        map.put("googleMapsUri",placeDetails.getGoogleMapsUri());
        map.put("websiteUri",placeDetails.getWebsiteUri());
        map.put("id",placeDetails.getId());
        namedParameterJdbcTemplate.update(sql, map);
        System.out.println("Update merchants : => " + placeDetails.getId());
    }

    //檢查排程，確認Merchants內有無資料的googleMapsUri與websiteUri為null。
    public List<Merchants> selectEmptyUri(){
        List<Merchants> merchantsList = null;
        String selectAll = "SELECT * FROM merchants WHERE googleMapsUri = '' AND websiteUri IS NULL ";
        merchantsList = namedParameterJdbcTemplate.query(selectAll, new MerchantsRowMapper());
        System.out.println("selsectAll, ==>" + merchantsList);
        if (merchantsList.isEmpty()) {
            return null;
        }
        return merchantsList;
    }
}
