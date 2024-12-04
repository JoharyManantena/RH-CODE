package com.rh.controller.BackOffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rh.controller.BaseController;
import com.rh.model.BackOffice.Admin;
import com.rh.service.BackOffice.AdminService;

@Controller
public class AuthController extends BaseController {

    private final AdminService adminService; 
    public AuthController(AdminService as) {
        this.adminService = as;
    }

    @PostMapping("/checkLoginAdmin")
    public String checkLoginAdmin(
            @RequestParam("username") String username,
            @RequestParam("password") String passeord,
            RedirectAttributes redirectAttributes) {
        
        try {
            Admin admin = this.adminService.checkLogin(username, passeord);
        } catch (Exception ex) {
            System.out.println("erreur " + ex);
            redirectAttributes.addAttribute("erreurLogin", ex.getMessage());
            return "redirect:/";
        }

        return "redirect:/accueil";
    }
}
