package com.tutorial.carreraservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarreraServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarreraServiceApplication.class, args);
	}

}
