package com.example.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    @Column(nullable = false)
    private LocalDate dateCreation;
    @Column(nullable = false)
    private String role;  

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Compte> comptes;
}