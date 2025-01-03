package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.model.Admin;

import com.example.repository.AdminRepository;


@Service
public class AdminService {
	@Autowired
    private AdminRepository AdminRepository;
	
	public Admin saveAdmin(Admin Admin) {
        return AdminRepository.save(Admin);
    }
}
