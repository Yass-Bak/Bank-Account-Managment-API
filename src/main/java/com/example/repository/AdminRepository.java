package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.model.Admin;
//extends , CrudRepository<Admin, Long>
public interface AdminRepository extends JpaRepository<Admin, Long>  {
    Admin findByEmail(String email);
}