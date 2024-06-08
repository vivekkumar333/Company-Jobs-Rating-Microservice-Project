package com.learnwithtiwari.companyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CompanyServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);
	}
}
