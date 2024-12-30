package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.Compte;
import com.example.service.ClientService;
import com.example.service.CompteService;

import java.util.List;

@Controller
@RequestMapping("/admin/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listComptes(Model model) {
        List<Compte> comptes = compteService.getAllComptes();
        model.addAttribute("comptes", comptes);
        return "admin/comptes";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("compte", new Compte());
        model.addAttribute("clients", clientService.getAllClients());
        return "admin/create-compte";
    }

    @PostMapping
    public String createCompte(@ModelAttribute("compte") Compte compte) {
        compteService.saveCompte(compte);
        return "redirect:/admin/comptes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Compte compte = compteService.getCompteById(id);
        model.addAttribute("compte", compte);
        model.addAttribute("clients", clientService.getAllClients());
        return "admin/edit-compte";
    }

    @PostMapping("/{id}")
    public String updateCompte(@PathVariable("id") Long id, @ModelAttribute("compte") Compte compte) {
        compte.setId(id);
        compteService.saveCompte(compte);
        return "redirect:/admin/comptes";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompte(@PathVariable("id") Long id) {
        compteService.deleteCompte(id);
        return "redirect:/admin/comptes";
    }
}