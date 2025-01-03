package com.example.model;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCompte;
    private Double solde;
    @Column(nullable = false)
    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    @PrePersist
    public void prePersist() {
        if (dateCreation == null) {
            dateCreation = LocalDate.now();
        }
    }
}