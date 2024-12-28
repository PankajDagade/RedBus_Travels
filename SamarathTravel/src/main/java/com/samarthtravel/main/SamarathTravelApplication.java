package com.samarthtravel.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SamarathTravelApplication {

	public static void main(String[] args) {
		System.out.println("This is Samarth Travels.");
		SpringApplication.run(SamarathTravelApplication.class, args);
	}

}
