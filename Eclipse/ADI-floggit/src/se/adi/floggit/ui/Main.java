package se.adi.floggit.ui;

import se.adi.floggit.classes.User;

public class Main
{

	public static void main(String[] args)
	{
		User andrea = new User("andreaberglund@hotmail.com", "password", "Andrea", "Berglund", 
				"Fogdevagen 44", "12841", "Bagarmossen", "08-555 55 55"); 
		
		System.out.println(andrea.toString());
	}

}
