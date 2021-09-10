package fr.noyersao.sortir.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.noyersao.sortir.model.User;
import fr.noyersao.sortir.repository.UserRepository;

@Controller
public class HomeController {
     
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
}
