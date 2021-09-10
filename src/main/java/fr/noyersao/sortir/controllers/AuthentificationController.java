package fr.noyersao.sortir.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import fr.noyersao.sortir.model.User;
import fr.noyersao.sortir.service.EmailService;
import fr.noyersao.sortir.service.UserService;

@Controller
public class AuthentificationController {

	private final UserService userService;
	private EmailService emailService;

	public AuthentificationController(UserService userService, EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;
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
			
			// Disable user until they click on confirmation link in email
		    user.setActif(false);
		    
		    //hash password
		    //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		    
		    // Generate random 36-character string token for confirmation link
		    user.setConfirmationToken(UUID.randomUUID().toString());
		        
		    //save user in database
		    userService.saveUser(user);
			
		    /*
		    //generate url to confirm email adress
			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			//send email with url confirm
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("noreply@domain.com");
			
			emailService.sendEmail(registrationEmail);
			
			modelMap.put("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
			return "account/register_form";
			*/
		    return "account/register_success";
		}
	}

	@GetMapping("/login")
	public String showLoginForm(ModelMap modelMap) {

		return "account/login_form";
	}
}
