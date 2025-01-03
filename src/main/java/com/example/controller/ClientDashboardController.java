package com.example.controller;

import com.example.model.Client;
import com.example.model.Compte;
import com.example.service.ClientService;
import com.example.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
@Controller
@RequestMapping("/client")
public class ClientDashboardController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CompteService compteService;

    // Assuming user is authenticated and we are using the authenticated user’s email
    @GetMapping("/dashboard")
    public String clientDashboard(@AuthenticationPrincipal User user, @RequestParam(value = "clientId", required = false) Long clientId, Model model) {
        try {
            // Use the authenticated user's email if clientId is not provided
            String actualClientEmail = (clientId != null) ? String.valueOf(clientId) : user.getUsername();

            // Retrieve the client by email
            Client client = clientService.getClientByEmail(actualClientEmail);
            if (client == null) {
                model.addAttribute("error", "Client not found");
                return "error";  // You can handle this better by showing a custom error page
            }

            // Calculate the total balance (solde) for the client
            double totalSolde = client.getComptes().stream()
                .mapToDouble(Compte::getSolde)
                .sum();

            // Add attributes to the model
            model.addAttribute("client", client);
            model.addAttribute("totalSolde", totalSolde);
            model.addAttribute("comptes", client.getComptes());

            return "client/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while fetching client data");
            e.printStackTrace(); // Log the error properly in production
            return "error";  // Return an error view
        }
    }
 
    
}

