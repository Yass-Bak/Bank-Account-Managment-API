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
import com.example.repository.ClientRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
        	logger.info("Client not found");
            throw new UsernameNotFoundException("Client not found");
            
        }
        logger.info("Client found: " + client.getEmail());
        logger.info("Password: " + client.getMotDePasse());
        return User.withUsername(client.getEmail())
                .password(client.getMotDePasse())
                .roles("CLIENT")
                .build();
    }
}