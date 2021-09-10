package fr.noyersao.sortir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.noyersao.sortir.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	Optional<User> findByConfirmationToken(String confirmationToken);
}
