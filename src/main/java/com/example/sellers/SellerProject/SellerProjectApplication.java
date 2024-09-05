package com.example.sellers.SellerProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SellerProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerProjectApplication.class, args);
	}

}
