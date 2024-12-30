package com.example.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private LocalDateTime dateTransaction;
    private String type;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;
}