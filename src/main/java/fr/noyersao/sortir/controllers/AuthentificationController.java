package fr.noyersao.sortir.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import fr.noyersao.sortir.model.User;
import fr.noyersao.sortir.service.UserService;

@Controller
public class AuthentificationController {

	private final UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthentificationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String showRegistrationForm(ModelMap modelMap) {
		modelMap.put("user", new User());

		return "account/register_form";
	}

	@PostMapping("/process_register")
	public String processRegister(ModelMap modelMap, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
		
		System.out.println("user :");
		System.out.println(user.getEmail());

		// Lookup user in database by e-mail
		User userExists = userService.getUserByEmail(user.getEmail());

		System.out.println("userExists :");
		System.out.println(userExists);

		if (userExists != null) {
			modelMap.put("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			bindingResult.reject("email");
			return "account/register_form";
		}
		if (bindingResult.hasErrors()) { 
			return "account/register_form";	
		} else { // new user so we create user and send confirmation e-mail
		    
		    //hash password
		    user.setPassword(passwordEncoder.encode(user.getPassword()));
		        
		    //save user in database
		    userService.saveUser(user);
			
		    return "account/register_success";
		}
	}

	@GetMapping("/login")
	public String showLoginForm(ModelMap modelMap) {

		return "account/login_form";
	}
	
	/*
	@PostMapping(/process_login)
	public String processLogin() {
		
	}
	*/
}
