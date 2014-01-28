package se.adi.floggit.ui;

import se.adi.floggit.api.UserRepositoryInDB;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public class Main {
	public static void main(String[] args) {
		Webshop webshop = new Webshop(new UserRepositoryInDB());
		User isak = new User("dannie@hotmail.com", "secret", "Dannie", "Håkansson", "Plommonvägen 4", "263 65", "Viken");
		
		if (webshop.updateUser("dannie@hotmail.com", isak)) {
			System.out.println(isak.toString() + "was updated");
		} else {
			System.out.println("email was not found in the system!");
		}
		
//		for (User u : webshop.readAllUsers()) {
//			System.out.println(u);
//		}
	}
}
