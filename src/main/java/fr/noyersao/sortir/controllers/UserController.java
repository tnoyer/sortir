package fr.noyersao.sortir.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import fr.noyersao.sortir.model.User;
import fr.noyersao.sortir.repository.UserRepository;

@Controller
public class UserController {

	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public String listUsers(ModelMap modelMap) {
	    List<User> listUsers = userRepository.findAll();
	    modelMap.put("listUsers", listUsers);
	     
	    return "user/index";
	}
}
