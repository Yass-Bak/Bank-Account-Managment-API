package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan(basePackages = "com.example.model")
@EnableJpaRepositories(basePackages = "com.example.repository") 
@ComponentScan(basePackages = {"com.example.controller","com.example.service","com.example.config"}) 
@SpringBootApplication
public class GestionBanqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionBanqueApplication.class, args);
		System.out.println("Started !");
	}

}
