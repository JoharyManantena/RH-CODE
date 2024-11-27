package com.rh.controller.BackOffice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rh.controller.BaseController;

@Controller
public class HomeController extends BaseController{
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageContent", "Backoffice/creationOffre");
        return "layout";
    }

    @GetMapping("/loginAdmin")
    public String login(Model model) {
        return "BackOffice/login";
    }
}
