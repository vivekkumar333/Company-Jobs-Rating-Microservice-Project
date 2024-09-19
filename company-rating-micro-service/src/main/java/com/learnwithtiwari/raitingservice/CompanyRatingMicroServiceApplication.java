package com.learnwithtiwari.raitingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CompanyRatingMicroServiceApplication {

	@Bean
	@LoadBalanced	// Enable Microservice's to do inter-communication using SERVICE-NAME registered with service-registry.
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CompanyRatingMicroServiceApplication.class, args);
	}

}
