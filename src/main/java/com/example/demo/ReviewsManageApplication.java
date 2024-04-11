package com.example.demo;

import com.example.Entites.PlaceSearchRes;
import com.example.scheduler.configuration.QuartzConfig;
import com.example.scheduler.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;
import java.io.IOException;


@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@RestController
public class ReviewsManageApplication {
	AtomicReference valueRef = new AtomicReference<>();
	String responseText = "";
	private final JsonToPojoService jtpService;
	private final MerchantsDAO merchantsDAO;
	private final AsyncMerchantsService asyncMerchantsService;
	private static  QuartzConfig quartzConfig;
	@Autowired
	public ReviewsManageApplication(QuartzConfig quartzConfig, MerchantsDAO merchantsDAO, JsonToPojoService jtpService, AsyncMerchantsService asyncMerchantsService, QuartzConfig quartzConfig1) {
		this.jtpService = jtpService;
		this.asyncMerchantsService= asyncMerchantsService;
		this.merchantsDAO = merchantsDAO;
		//this.quartzConfig = quartzConfig;
        this.quartzConfig = quartzConfig;
    }
	public static void main(String[] args) throws SchedulerException {
		//Spring主程式
		SpringApplication.run(ReviewsManageApplication.class, args);
		//設置排程
		StartDailyScheduler();
	}
	public static void StartDailyScheduler() throws SchedulerException {
		quartzConfig.schedulerStart();
	}
	@GetMapping("/getPlaceId")
	public String TestHttpReq() throws IOException {
		String res = asyncMerchantsService.callMerchantsAPI();
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
		return  "testGetPlaceId";
	}
}