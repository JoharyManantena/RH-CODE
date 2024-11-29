package com.rh.service.BackOffice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rh.model.BackOffice.Admin;
import com.rh.repository.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository ar) {
        this.adminRepository = ar;
    }

    public Admin checkLogin(String username, String password) throws Exception{
        Optional<Admin> user = adminRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("Nom d'utilisateur ou mot de passe incorrect");
        }
    }
}
