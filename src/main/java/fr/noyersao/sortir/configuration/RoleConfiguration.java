package fr.noyersao.sortir.configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.noyersao.sortir.model.Role;
import fr.noyersao.sortir.repository.RoleRepository;

@Configuration
public class RoleConfiguration {

	@Bean
	CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
		return args -> {
			Role user = new Role("ROLE_USER", "User Role");
			Role admin = new Role("ROLE_ADMIN", "Admin Role");
			roleRepository.saveAll(List.of(user, admin));
		};
	}
}
