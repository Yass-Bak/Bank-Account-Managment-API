package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.model.Client;
import com.example.model.Admin; // Assurez-vous d'importer la classe Admin
import com.example.repository.ClientRepository;
import com.example.repository.AdminRepository; // Assurez-vous d'importer AdminRepository

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository; // Ajouter l'AdminRepository

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Vérification du Client
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            logger.info("Client found: " + client.getEmail());
            return User.withUsername(client.getEmail())
                    .password(client.getMotDePasse())  // Password (already encoded)
                    .roles(client.getRole())  // Client's role
                    .build();
        }

        // Vérification de l'Admin si le Client n'est pas trouvé
        Admin admin = adminRepository.findByEmail(email); // Chercher dans AdminRepository
        if (admin != null) {
            logger.info("Admin found: " + admin.getEmail());
            return User.withUsername(admin.getEmail())
                    .password(admin.getMotDePasse())  // Password (already encoded)
                    .roles(admin.getRole())  // Admin's role
                    .build();
        }

        // Si aucun utilisateur trouvé
        logger.info("No user found with email: " + email);
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
