package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoginController {
	 @GetMapping("/login")
	    public String loginPage() {
	        return "login"; // Assurez-vous que le fichier 'login.html' existe dans /src/main/resources/templates
	    }
    @RequestMapping("/defaultRedirect")
    public String defaultRedirect(Authentication authentication) {
        try {
            if (authentication != null) {
                String role = authentication.getAuthorities().stream()
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .findFirst()
                        .orElse(""); // Default to empty string if no role is found

                // Log the role for debugging
                System.out.println("User Role: " + role);

                // Redirect based on the role
                if (role.equals("ROLE_ADMIN")) {
                    return "redirect:/admin/dashboard";
                } else if (role.equals("ROLE_CLIENT")) {
                    return "redirect:/client/dashboard";
                }
            }
            // Default redirect if no specific role is found
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace(); // Log exception for debugging
            return "redirect:/error";
        }
    }
}
