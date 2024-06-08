package com.learnwithtiwari.service.registory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JobCompanyRatingServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobCompanyRatingServiceRegistryApplication.class, args);
	}

}
