package se.adi.floggit.ui;

import se.adi.floggit.api.ResponseType;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public class TestAndrea
{

	public static void main(String[] args)
	{
		Webshop webshop = new Webshop(); 
		User andrea = new User("andreaberglund@hotmail.com", "password", "Andrea", "Berglund", "Fogdevägen 44", "12841", "Bagarmossen", "0703436251"); 
		User anders = new User("isak@bergen.se", "password", "Andrea", "Berglund", "Fogdevägen 44", "12841", "Bagarmossen", "0703436251"); 
		
//		ResponseType response = webshop.createUser(andrea);
//		if (response == ResponseType.USER_CREATED)
//		{
//			System.out.println("User was created in DB!");
//		}
//		else if (response == ResponseType.USER_NOT_CREATED)
//		{
//			System.out.println("Error! Username/Email was already registered in DB.");
//		}
//		else
//		{
//			System.out.println("Creation of user failed because of server and DB communication problems");
//		}
		
	}

}
