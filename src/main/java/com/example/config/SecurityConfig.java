package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsService userDetailsService;

	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable() // If you need to disable CSRF, otherwise remove this line
				.authorizeRequests().requestMatchers("/login").permitAll().requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Allow login to everyone
				.requestMatchers("/admin/**").hasRole("ADMIN") // Only Admins can access /admin/*
				.requestMatchers("/client/**").hasRole("CLIENT") // Only Clients can access /client/*
				.anyRequest().authenticated() // Any other request needs authentication
				.and().formLogin().loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/defaultRedirect", true).and().logout().logoutSuccessUrl("/login?logout=true");

		return http.build();
	}

	
}
