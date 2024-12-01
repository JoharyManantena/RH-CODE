package com.rh.controller.FrontOffice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rh.dto.LoginRequest;
import com.rh.model.FrontOffice.Candidat;
import com.rh.model.FrontOffice.OffreEmploi;
import com.rh.service.FrontOffice.CandidatService;
import com.rh.service.FrontOffice.OffreEmploiService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final CandidatService candidatService;
    private final OffreEmploiService offreEmploiService;

    public AuthController(CandidatService cs, OffreEmploiService oeS) {
        this.candidatService = cs;
        this.offreEmploiService = oeS;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        Candidat candidat = candidatService.authenticate(request.getEmail(), request.getPassword());
        if (candidat != null) {
            // Stocker l'utilisateur dans la session
            session.setAttribute("candidat", candidat);
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @GetMapping("/auth/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Détruire la session
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/auth/me")
    public ResponseEntity<Candidat> getCurrentUser(HttpSession session) {
        Candidat candidat = (Candidat) session.getAttribute("candidat");
        if (candidat != null) {
            return ResponseEntity.ok(candidat);
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/offres")
    public ResponseEntity<List<OffreEmploi>> getOffres() {
        try {
            List<OffreEmploi> offres = offreEmploiService.getAll();
            return ResponseEntity.ok(offres);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
