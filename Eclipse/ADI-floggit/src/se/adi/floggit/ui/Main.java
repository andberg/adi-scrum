package se.adi.floggit.ui;

import se.adi.floggit.api.UserRepositoryInDB;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public class Main {
	public static void main(String[] args) {
		Webshop webshop = new Webshop(new UserRepositoryInDB());
		User isak = new User("lol@hotmail.com", "secret", "Dannie", "Håkansson", "Plommonvägen 4", "263 65", "Viken");
		
		if (webshop.updateUser("lol@hotmail.com", isak)) {
			System.out.println(isak.toString() + "was created");
		} else {
			System.out.println("Email was in use!");
		}
		
//		for (User u : webshop.readAllUsers()) {
//			System.out.println(u);
//		}
	}
}
