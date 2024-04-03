package com.example.demo;

import com.example.Entites.Place;
import com.example.scheduler.model.eventPOJO;
import com.example.scheduler.service.JsonToPojoService;
import com.example.scheduler.service.MapService;
import com.example.scheduler.service.eventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.scheduler.service.eventService;
import java.util.concurrent.atomic.AtomicReference;
import java.io.IOException;
import java.util.Optional;


@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@RestController
public class ReviewsManageApplication {
	AtomicReference valueRef = new AtomicReference<>();
	private final eventService eventService;
	private final MapService mapService;
	private final JsonToPojoService jtpService;

	@Autowired
	public ReviewsManageApplication(eventService eventService,MapService mapService,JsonToPojoService jtpService ) {
		this.eventService = eventService;
		this.mapService = mapService;
		this.jtpService = jtpService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReviewsManageApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/test")
	public String test() throws JsonProcessingException {
		//測試用
		//eventService.fetchEventById(1).subscribe(eventPOJO -> System.out.print(eventPOJO.getName()));
		//正式
		//mapService.fetchPlaceBytext("").subscribe(System.out::println);
		mapService.fetchPlaceBytext("").subscribe(
				value -> {
					valueRef.set(value);
					System.out.println("compelete set.."+value);
				},
				error -> {
					error.printStackTrace();
				}
		);
		System.out.println("SuCCESS");
		System.out.println("valueRef==>"+valueRef.get() );
				//.subscribe(Place -> System.out.println(Place.getArrPlaceDetail()[0].getDisplayName())
		/*
		Place testPlace = jtpService.convert(result, Place.class );
		System.out.println("testPlace===>"+testPlace.getPlace().length);
		System.out.println("testPlace===>"+testPlace);
		*/

		return String.format("test here!");

	}

	@GetMapping("/test123ok")
	public String testOK() {
		OkHttpClient client = new OkHttpClient();

		// 创建JSON请求体
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		String jsonBody = "{\"textQuery\": \"Spicy Vegetarian Food in Sydney, Australia\"}";
		RequestBody requestBody = RequestBody.create(jsonBody, JSON);

		// 创建POST请求，并添加自定义标头
		Request request = new Request.Builder()
				.url("https://places.googleapis.com/v1/places:searchText")
				.post(requestBody)
				.addHeader("Content-Type", "application/json")
				.addHeader("X-Goog-Api-Key", "AIzaSyCppk2hdPJk7w5jrZuLs8-4EtdU1V7NmZA")
				.addHeader("X-Goog-FieldMask", "places.displayName,places.formattedAddress,places.id")
				.build();

		// 发送请求并处理响应
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				// 获取响应数据
				String responseData = response.body().string();
				System.out.println("Response: " + responseData);
			} else {
				System.out.println("Request failed");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "in test ok ";
	}
}