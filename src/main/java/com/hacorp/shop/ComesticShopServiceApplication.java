package com.hacorp.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.hacorp.shop"})
public class ComesticShopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComesticShopServiceApplication.class, args);
	}

}
