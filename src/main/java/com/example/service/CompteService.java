package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Compte;
import com.example.repository.CompteRepository;

import java.util.List;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Compte getCompteById(Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }
    public List<Compte> getComptesByClientId(Long userId) {
        
        return compteRepository.findByClientId(userId);
    }
}