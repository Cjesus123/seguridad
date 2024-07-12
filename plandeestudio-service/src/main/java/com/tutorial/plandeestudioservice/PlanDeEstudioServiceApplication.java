package com.tutorial.plandeestudioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlanDeEstudioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanDeEstudioServiceApplication.class, args);
	}

}
