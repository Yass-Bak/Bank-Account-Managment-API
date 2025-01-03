package com.example.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.model.Admin;
import com.example.model.Client;
import com.example.model.Compte;
import com.example.model.Transaction;
import com.example.repository.AdminRepository;
import com.example.repository.ClientRepository;
import com.example.repository.CompteRepository;
import com.example.repository.TransactionRepository;

@Configuration
public class DataInitializer {
/*
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Encode password for Client and Admin
            String encodedPassword = encoder.encode("password");

            // Create and save Client 1
            Client client1 = new Client();
            client1.setNom("John");
            client1.setPrenom("Doe");
            client1.setEmail("john.doe@example.com");
            client1.setMotDePasse(encodedPassword);
            client1.setDateCreation(LocalDate.of(2023, 1, 1));
            client1.setRole("CLIENT");

            // Create and save Client 2
            Client client2 = new Client();
            client2.setNom("Jane");
            client2.setPrenom("Smith");
            client2.setEmail("jane.smith@example.com");
            client2.setMotDePasse(encodedPassword);
            client2.setDateCreation(LocalDate.of(2023, 1, 2));
            client2.setRole("CLIENT");

            // Save Clients to database
            clientRepository.save(client1);
            clientRepository.save(client2);
            System.out.println("Clients saved");

            // Create and save Admin
            Admin admin = new Admin();
            admin.setEmail("admin@example.com");
            admin.setMotDePasse(encodedPassword);
            admin.setRole("ADMIN");

            adminRepository.save(admin);
            System.out.println("Admin saved");

            // Create and save Comptes for Clients
            Compte compte1 = new Compte();
            compte1.setNumeroCompte("1234567890");
            compte1.setSolde(1000.0);
            compte1.setDateCreation(LocalDate.of(2023, 1, 1));
            compte1.setClient(client1);
            compteRepository.save(compte1);

            Compte compte2 = new Compte();
            compte2.setNumeroCompte("0987654321");
            compte2.setSolde(2000.0);
            compte2.setDateCreation(LocalDate.of(2023, 1, 2));
            compte2.setClient(client2);
            compteRepository.save(compte2);
            System.out.println("Comptes saved");

            // Create and save Transactions for Comptes
            Transaction transaction1 = new Transaction();
            transaction1.setMontant(100.0);
            transaction1.setDateTransaction(LocalDateTime.of(2023, 1, 1, 10, 0));
            transaction1.setType("CREDIT");
            transaction1.setCompte(compte1);
            transactionRepository.save(transaction1);

            Transaction transaction2 = new Transaction();
            transaction2.setMontant(-50.0);
            transaction2.setDateTransaction(LocalDateTime.of(2023, 1, 2, 11, 0));
            transaction2.setType("DEBIT");
            transaction2.setCompte(compte1);
            transactionRepository.save(transaction2);

            Transaction transaction3 = new Transaction();
            transaction3.setMontant(200.0);
            transaction3.setDateTransaction(LocalDateTime.of(2023, 1, 3, 12, 0));
            transaction3.setType("CREDIT");
            transaction3.setCompte(compte2);
            transactionRepository.save(transaction3);

            Transaction transaction4 = new Transaction();
            transaction4.setMontant(-100.0);
            transaction4.setDateTransaction(LocalDateTime.of(2023, 1, 4, 13, 0));
            transaction4.setType("DEBIT");
            transaction4.setCompte(compte2);
            transactionRepository.save(transaction4);
            System.out.println("Transactions saved");
        };
    }
*/}
