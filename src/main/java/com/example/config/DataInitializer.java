package com.example.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.model.Admin;
import com.example.model.Client;
import com.example.repository.AdminRepository;
import com.example.repository.ClientRepository;

@Configuration
public class DataInitializer {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AdminRepository AdminRepository;

	@Bean
	public CommandLineRunner initData() {
		System.out.println("Seeding Data");
		return args -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode("password");

			Client client = new Client();
			client.setNom("John");
			client.setPrenom("Doe");
			client.setEmail("yassine@yassine.com");
			client.setMotDePasse(encodedPassword);
			client.setDateCreation(LocalDate.now());
			client.setRole("CLIENT");

			clientRepository.save(client);
			System.out.println("Client saved");
			
			
			// Seed Admin
            Admin admin = new Admin();
            admin.setEmail("admin@admin.com");
            admin.setMotDePasse(encodedPassword);
            admin.setRole("ADMIN");
            AdminRepository.save(admin);
            System.out.println("Admin saved");
		};
	}
}