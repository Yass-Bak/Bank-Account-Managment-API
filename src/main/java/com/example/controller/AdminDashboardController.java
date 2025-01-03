package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//AdminController.java
@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

	@GetMapping("/dashboard")
	public String adminDashboard() {
		try {
			return "admin/dashboard";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		// Will resolve to src/main/resources/templates/admin/dashboard.html
	}
}
