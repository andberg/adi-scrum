package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.classes.User;

public interface UserRepository {
	boolean createUser(User user);
	List<User> readAllUsers();
	boolean updateUser(String email, User user);
	boolean deleteUser(String email);
	boolean login(String email, String password);
}
