package com.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	
	 List<Compte> findByClientId(Long userId);
}