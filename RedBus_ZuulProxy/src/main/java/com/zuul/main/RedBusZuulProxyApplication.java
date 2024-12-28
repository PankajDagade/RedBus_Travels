package com.zuul.main;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class RedBusZuulProxyApplication {

	public static void main(String[] args) {
		System.out.println("This is Zuul Proxy");
		SpringApplication.run(RedBusZuulProxyApplication.class, args);
	}

}
