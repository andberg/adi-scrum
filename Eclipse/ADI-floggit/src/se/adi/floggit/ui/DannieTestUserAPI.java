package se.adi.floggit.ui;

import java.util.List;

import se.adi.floggit.api.Response;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public class DannieTestUserAPI
{
	public static void main(String[] args)
	{
		Webshop webshop = new Webshop();
		
		Response<List<User>> response = webshop.readAllUsers();
		
		for (User user : response.getObject()) {
			System.out.println(user);
		}
		System.out.println(response.getResponse());
	}
}
