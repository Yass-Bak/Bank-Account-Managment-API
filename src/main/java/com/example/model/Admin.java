package com.example.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String motDePasse;
    private String role;
}