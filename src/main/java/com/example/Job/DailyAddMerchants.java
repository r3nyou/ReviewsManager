package com.example.Job;

import com.example.Entites.PlaceSearchRes;
import com.example.scheduler.configuration.getBeanUtils;
import com.example.scheduler.service.AsyncMerchantsService;
import com.example.scheduler.service.JsonToPojoService;
import com.example.scheduler.service.MerchantsDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
@Component
public class DailyAddMerchants implements Job {
    AtomicReference valueRef = new AtomicReference<>();
    String responseText = "";
    private AsyncMerchantsService asyncMerchantsService;
    private  JsonToPojoService jtpService;
    private MerchantsDAO merchantsDAO;
    /*
    @Autowired
     */
    /*
    public  DailyAddMerchants(AsyncMerchantsService asyncMerchantsService, JsonToPojoService jtpService, MerchantsDAO merchantsDAO) {
        this.asyncMerchantsService = asyncMerchantsService;
        this.jtpService = jtpService;
        this.merchantsDAO = merchantsDAO;
    }
    */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        asyncMerchantsService = (AsyncMerchantsService)getBeanUtils.getBean(AsyncMerchantsService.class);
        jtpService = (JsonToPojoService)getBeanUtils.getBean(JsonToPojoService.class);
        merchantsDAO = (MerchantsDAO)getBeanUtils.getBean(MerchantsDAO.class);
        String res = null;
        try {
            res = asyncMerchantsService.callMerchantsAPI();
            System.out.println("DailyAdd_RES==>"+res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Response from API: " + res);
        PlaceSearchRes placeSearchRes = new PlaceSearchRes();
        try {
            placeSearchRes = jtpService.convert(res, PlaceSearchRes.class);
            System.out.println("printMerchantsProps==>");
            placeSearchRes.printMerchantsProps();
            System.out.println("merchants.getResults()[0]==>");
            placeSearchRes.getResults()[0].printMerchantsProps();
            merchantsDAO.insert(placeSearchRes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}