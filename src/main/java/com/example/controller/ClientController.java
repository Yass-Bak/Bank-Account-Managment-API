package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.Client;
import com.example.service.ClientService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/clients")
public class ClientController {
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private ClientService clientService;
	

	@GetMapping
	public String listClients(Model model) {
		try {
			List<Client> clients = clientService.getAllClients();
			model.addAttribute("clients", clients);
			return "admin/clients";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("client", new Client());
		return "admin/create-client";
	}

	@PostMapping
	public String createClient(@ModelAttribute("client") Client client) {
		try {
			client.setRole("CLIENT");
			client.setDateCreation(LocalDate.now());
			String encodedPassword = passwordEncoder.encode(client.getMotDePasse());
	        client.setMotDePasse(encodedPassword);
			clientService.saveClient(client);
			return "redirect:/admin/clients";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Client client = clientService.getClientById(id);
		model.addAttribute("client", client);
		return "admin/edit-client";
	}

	@PostMapping("/{id}")
	public String updateClient(@PathVariable("id") Long id, @ModelAttribute("client") Client client) {
		client.setId(id);
		clientService.saveClient(client);
		return "redirect:/admin/clients";
	}

	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable("id") Long id) {
		clientService.deleteClient(id);
		return "redirect:/admin/clients";
	}
}