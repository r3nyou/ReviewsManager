package com.example.Job;
import com.example.Entites.Merchants;
import com.example.Entites.PlaceDetails;
import com.example.Entites.PlaceSearchRes;
import com.example.scheduler.service.CustomReviewsDAO;
import com.example.scheduler.service.CustomReviewsService;
import com.example.scheduler.service.JsonToPojoService;
import com.example.scheduler.service.MerchantsDAO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.example.scheduler.configuration.getBeanUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DailyCheckDetails implements Job {
    private MerchantsDAO merchantsDAO;
    private CustomReviewsDAO customReviewsDAO;
    private CustomReviewsService customReviewsService;
    private JsonToPojoService jsonToPojoService;
    List<Merchants> MerchantsList;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        merchantsDAO = getBeanUtils.getBean(MerchantsDAO.class);
        customReviewsDAO = getBeanUtils.getBean(CustomReviewsDAO.class);
        customReviewsService = getBeanUtils.getBean(CustomReviewsService.class);
        jsonToPojoService = getBeanUtils.getBean(JsonToPojoService.class);
        MerchantsList = merchantsDAO.selectEmptyUri();
        for(int i = 0; i < MerchantsList.size(); i++) {
            try {
                String res = customReviewsService.callPlaceDetailsAPI(MerchantsList.get(i).getPlaceId());
                PlaceDetails placeDetails = jsonToPojoService.convert(res, PlaceDetails.class);
                System.out.println("地點 "+MerchantsList.get(i).getDisplayName()+"， PlaceDetails_Response==>");
                placeDetails.printAllProps();
                //如有客戶評論紀錄到customerReviews
                if(placeDetails.getReviews() != null){
                    System.out.println("地點 "+MerchantsList.get(i).getDisplayName()+"，客戶評論紀錄到customerReviews");
                    customReviewsDAO.insert(placeDetails);
                }
                //若有官網&定位連結，更新到商家主表
                if(!Objects.equals(placeDetails.getGoogleMapsUri(), "") || placeDetails.getWebsiteUri()!=null){
                    merchantsDAO.update(placeDetails);
                    System.out.println("地點 "+MerchantsList.get(i).getDisplayName()+"，官網&定位連結，更新到商家主表");
                }
                System.out.println("done");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
       }
        /*
        //todo..查Details
        try {
            String res = customReviewsService.callPlaceDetailsAPI(MerchantsList.get(1).getPlaceId());
            System.out.println("詳細回傳==> "+ res);
            //轉為POJO
            PlaceDetails placeDetails = jsonToPojoService.convert(res, PlaceDetails.class);
            System.out.println("列印出placeDetails==>");
            placeDetails.printAllProps();
            //呼叫Details DAO
            if(placeDetails.getReviews() != null){
                //如有評論就寫入customerReviews
                customReviewsDAO.insert(placeDetails);
            }
            if(!Objects.equals(placeDetails.getGoogleMapsUri(), "") || placeDetails.getWebsiteUri()!=null){
                //更新商家主表merchants
                merchantsDAO.update(placeDetails);
                System.out.println("solution2");
            }
            System.out.println("看DB");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        /*
        //寫一個好了再回圈
        for(int i = 0; i < MerchantsList.size(); i++) {
            // +MerchantsList.get(0).getId());
            try {
                String res = customReviewsService.callPlaceDetailsAPI(MerchantsList.get(0).getPlaceId());
                System.out.println("res=>"+res);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        */
    }
}
