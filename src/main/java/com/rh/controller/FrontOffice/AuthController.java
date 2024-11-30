package com.rh.controller.FrontOffice;

import com.rh.dto.LoginRequest;
import com.rh.model.FrontOffice.Candidat;
import com.rh.service.FrontOffice.CandidatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final CandidatService candidatService;

    public AuthController(CandidatService cs) {
        this.candidatService = cs;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        Candidat candidat = candidatService.authenticate(request.getEmail(), request.getPassword());
        if (candidat != null) {
            // Stocker l'utilisateur dans la session
            session.setAttribute("candidat", candidat);
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Détruire la session
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<Candidat> getCurrentUser(HttpSession session) {
        Candidat candidat = (Candidat) session.getAttribute("candidat");
        if (candidat != null) {
            return ResponseEntity.ok(candidat);
        }
        return ResponseEntity.status(401).build();
    }
}
