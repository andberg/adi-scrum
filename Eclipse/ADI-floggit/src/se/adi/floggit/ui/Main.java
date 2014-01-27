package se.adi.floggit.ui;

import se.adi.floggit.classes.Product;
import se.adi.floggit.classes.User;

public class Main
{

	public static void main(String[] args)
	{
		User andrea = new User("andreaberglund@hotmail.com", "password", "Andrea", "Berglund", 
				"Fogdevagen 44", "12841", "Bagarmossen", "08-555 55 55"); 
		
		System.out.println(andrea.toString());
		
		Product test = new Product("En Sak", "En jattehaftig sak som gor saker", 999, 990, "Toys", "Garden", "Books");
		
		System.out.println(test.toString());
		
		
	}

}
