package se.adi.floggit.ui;

import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public class IsakTest
{

	public static void main(String[] args)
	{
		Webshop ws = new Webshop();

		User user1 = new User("1@isak.se", "Pass", "Isak", "Prytz", "Gata", "123434", "Sthlm");
		User user2 = new User("2@isak.se", "Pass", "Isak", "Prytz", "Gata", "123434", "Sthlm");

		System.out.println(ws.createUser(user1));
		System.out.println(ws.createUser(user2));

		System.out.println(ws.updateUser("notInDb@email", user1));

		System.out.println(ws.updateUser("1@isak.se", user1));

		System.out.println(ws.updateUser("1@isak.se", user2));

		System.out.println(ws.updateUser("2@isak.se", user2));

		System.out.println(ws.deleteUser("1@isak.se"));
		System.out.println(ws.deleteUser("2@isak.se"));


	}

}