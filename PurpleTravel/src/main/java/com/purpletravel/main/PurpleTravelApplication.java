package com.purpletravel.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PurpleTravelApplication {

	public static void main(String[] args) {
		System.out.println("This is Purple Travel.");
		SpringApplication.run(PurpleTravelApplication.class, args);
		
	}

}
