package com.projet.microservices.chauffeur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChauffeurApplication {

	public static void main(String[] args) {
		System.out.println("server started");
		SpringApplication.run(ChauffeurApplication.class, args);
	}

}
