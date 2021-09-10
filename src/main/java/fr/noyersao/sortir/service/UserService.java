package fr.noyersao.sortir.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.noyersao.sortir.model.User;
import fr.noyersao.sortir.repository.UserRepository;

@Service("userService")
public class UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUserByEmail(String email) {
		final User user = userRepository.findByEmail(email);
		/*
		if (!optionalUser.isPresent()) {
			throw new IllegalStateException("L'utilisateur n'existe pas");
		}
		*/
		return user;
	}
	
	public Optional<User> findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}
	
	public void saveUser(User user) {
		final User userExist = userRepository.findByEmail(user.getEmail());
		/*
		if (userExist != null) {
			throw new IllegalStateException("L'utilisateur existe déjà");
		}
		*/
		userRepository.save(user);
	}
}