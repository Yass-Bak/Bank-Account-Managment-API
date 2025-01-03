package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Client findByEmail(String email);
}