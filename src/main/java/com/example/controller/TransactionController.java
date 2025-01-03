package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.Client;
import com.example.model.Compte;
import com.example.model.Transaction;
import com.example.service.ClientService;
import com.example.service.CompteService;
import com.example.service.TransactionService;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/client/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CompteService compteService;
    @Autowired
    private ClientService ClientService;

    @GetMapping("/{compteId}")
    public String listTransactions(@PathVariable("compteId") Long compteId, Model model) {
        List<Transaction> transactions = transactionService.getTransactionsByCompteId(compteId);
        model.addAttribute("transactions", transactions);

        model.addAttribute("compte", compteService.getCompteById(compteId));
       // System.out.println("Transactions: " + transactions.toString());

        return "client/transactions";
    }


    // Show form to create a new transaction
    @GetMapping("/new")
    public String showCreateForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        
        // Get the client associated with the logged-in user's email
        Client client = ClientService.getClientByEmail(email);
        
        // Fetch the list of accounts linked to the client
        List<Compte> comptes = compteService.getComptesByClientId(client.getId());
        
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("comptes", comptes);  // Add the list of accounts to the form
        return "client/create-transaction";
    }

    // Create a new transaction
    @PostMapping
    public String createTransaction(@ModelAttribute("transaction") Transaction transaction,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        // Set the transaction date to the current time
        transaction.setDateTransaction(LocalDateTime.now());
        
        // Get the logged-in client's email
        String email = userDetails.getUsername();
        Client client = ClientService.getClientByEmail(email);
        
        // Associate the transaction with the client (if needed)
      //  transaction.setClient(client);
        
        // Save the transaction
        transactionService.saveTransaction(transaction);
        return "redirect:/client/transactions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Transaction transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "client/edit-transaction";
    }

    @PostMapping("/{id}")
    public String updateTransaction(@PathVariable("id") Long id, @ModelAttribute("transaction") Transaction transaction) {
        transaction.setId(id);
        transactionService.saveTransaction(transaction);
        return "redirect:/client/transactions";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable("id") Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/client/transactions";
    }
}