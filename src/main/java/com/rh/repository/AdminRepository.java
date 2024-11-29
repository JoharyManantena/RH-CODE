package com.rh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Optional<Admin> findByUsernameAndPassword(String username, String password);
}
