package fr.noyersao.sortir.configuration;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.noyersao.sortir.model.Role;
import fr.noyersao.sortir.model.User;
import fr.noyersao.sortir.repository.RoleRepository;
import fr.noyersao.sortir.repository.UserRepository;

@Configuration
public class DataConfiguration {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository) {
		return args -> {
			Role user = new Role("ROLE_USER", "User Role");
			Role admin = new Role("ROLE_ADMIN", "Admin Role");
			roleRepository.saveAll(List.of(user, admin));
			//String email, String password, String firstName, String lastName, String phoneNumber
			User user1 = new User("theo@mail.fr", passwordEncoder.encode("123456"), "Theo", "Noyer", null);
			userRepository.saveAll(List.of(user1));
		};
	}
}
