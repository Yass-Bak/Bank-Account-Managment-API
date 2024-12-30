package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.Transaction;
import com.example.service.TransactionService;

import java.util.List;

@Controller
@RequestMapping("/client/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{compteId}")
    public String listTransactions(@PathVariable("compteId") Long compteId, Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "client/transactions";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "client/create-transaction";
    }

    @PostMapping
    public String createTransaction(@ModelAttribute("transaction") Transaction transaction) {
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