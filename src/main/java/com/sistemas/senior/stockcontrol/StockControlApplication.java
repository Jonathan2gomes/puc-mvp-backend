package com.sistemas.senior.stockcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StockControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockControlApplication.class, args);
	}

}
