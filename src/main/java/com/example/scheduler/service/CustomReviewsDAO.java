package com.example.scheduler.service;
import com.example.Entites.PlaceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CustomReviewsDAO {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public void insert(PlaceDetails placeDetails) {
        String sql = "INSERT INTO customerReviews(merchantId, rating, text, publishTime, createAt, updateAt) " +
                "VALUES (:merchantId, :rating, :text, :publishTime, :createAt, :updateAt)";
        for(int i=0;i<placeDetails.getReviews().length;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("merchantId",placeDetails.getId());
            map.put("rating",placeDetails.getReviews()[i].getRating());
            map.put("text",placeDetails.getReviews()[i].getText().getText());
            map.put("publishTime",Dateformatter(placeDetails.getReviews()[i].getPublishTime()));
            map.put("createAt", new Timestamp(System.currentTimeMillis()));
            map.put("updateAt", new Timestamp(System.currentTimeMillis()));
            System.out.println("Insert PlaceDetails Success => " + placeDetails.getId());
            System.out.println("Insert text  => " + placeDetails.getReviews()[i].getText());
            System.out.println("Insert text context => " + placeDetails.getReviews()[i].getText().getText());
            System.out.println("Insert rating  => " + placeDetails.getReviews()[i].getRating());
            System.out.println("Insert publishTime  => " +placeDetails.getReviews()[i].getPublishTime());
            namedParameterJdbcTemplate.update(sql, map);
        }
    }
    //**留言可能來自不同時區，要轉為台灣時區或保留..Todo
    public String Dateformatter(String Date){
        DateTimeFormatter Oriformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime temp = LocalDateTime.parse(Date,Oriformatter);
        String  result = temp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return result;
    }
}
/*
* // 假设此处的 publishTimeValue 是你从 API 中获取的日期时间字符串 '2023-12-26T02:02:22Z'
String publishTimeValue = "2023-12-26T02:02:22Z";

// 使用 DateTimeFormatter 将日期时间字符串解析为 LocalDateTime 对象
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
LocalDateTime publishTime = LocalDateTime.parse(publishTimeValue, formatter);

// 将 LocalDateTime 对象转换为符合数据库要求的格式
// 假设数据库中的 publishTime 字段接受的日期时间格式是 'yyyy-MM-dd HH:mm:ss'
String formattedPublishTime = publishTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

* */