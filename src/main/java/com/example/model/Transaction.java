package com.example.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime dateTransaction;
    private String type;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;
}