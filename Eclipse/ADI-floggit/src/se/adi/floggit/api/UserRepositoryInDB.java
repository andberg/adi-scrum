package se.adi.floggit.api;

import java.util.List;

import se.adi.floggit.classes.User;
import se.adi.floggit.interfaces.UserRepository;

public class UserRepositoryInDB implements UserRepository {

	@Override
	public User createUser(User user) {
		return null;
	}

	@Override
	public User readUser(String email) {
		return null;
	}
	
	@Override
	public List<User> readAllUsers() {
		return null;
	}

	@Override
	public User updateUser(String email, String newPassword) {
		return null;
	}

	@Override
	public User deleteUser(String email) {
		return null;
	}

	@Override
	public boolean login(String email, String password) {
		return false;
	}
}
