package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.api.Response;
import se.adi.floggit.api.ResponseType;
import se.adi.floggit.classes.User;

public interface UserRepository
{
	ResponseType createUser(User user);

	Response readAllUsers();

	ResponseType updateUser(String email, User user);

	ResponseType deleteUser(String email);

	ResponseType login(String email, String password);
}
