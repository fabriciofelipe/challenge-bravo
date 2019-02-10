package com.currency.conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChallengeBravoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeBravoApplication.class, args);
	}

}

