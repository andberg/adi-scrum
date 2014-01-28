package se.adi.floggit.webshop;

import java.util.List;

import se.adi.floggit.classes.User;
import se.adi.floggit.interfaces.UserRepository;

public final class Webshop {
	private final UserRepository userRepository;

	public Webshop(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean createUser(String string, User user) {
		return userRepository.createUser(user);
	}
	
	public List<User> readAllUsers() {
		return userRepository.readAllUsers();
	}
	
	public boolean updateUser(String email, User user) {
		return userRepository.updateUser(email, user);
	}
	
	public boolean deleteUser(String email){
		return userRepository.deleteUser(email);
	}
}
