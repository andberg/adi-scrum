package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.classes.User;

public interface UserRepository {
	User createUser(User user);
	List<User> readAllUsers();
	User updateUser(String email, String newPassword);
	User deleteUser(String email);
	boolean login(String email, String password);
}
