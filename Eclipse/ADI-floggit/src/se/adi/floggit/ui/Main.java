package se.adi.floggit.ui;

import se.adi.floggit.api.UserRepositoryInDB;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public class Main {
	public static void main(String[] args) {
		Webshop webshop = new Webshop(new UserRepositoryInDB());
		//User isak = new User("dannie@hotmail.com", "secret", "Dannie", "Håkansson", "Plommonvägen 4", "263 65", "Viken");
		
		if (webshop.login("Nullam.lobortis@ipsumnuncid.com", "nascetur")) {
			System.out.println("loged in");
		} else {
			System.out.println("invalid email or password");
		}
		
//		for (User u : webshop.readAllUsers()) {
//			System.out.println(u);
//		}
	}
}
