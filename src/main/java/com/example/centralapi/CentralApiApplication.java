package com.example.centralapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CentralApiApplication {

	public WebClient webClient(){
		return WebClient.builder().build();
	}

//	@Bean
//	public WebClient webClient(){
//		return WebClient.builder().build();
//	}
	public static void main(String[] args) {
		SpringApplication.run(CentralApiApplication.class, args);
	}

}
