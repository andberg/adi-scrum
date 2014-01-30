package se.adi.floggit.ui;

import se.adi.floggit.classes.Product;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public class AndreaTestMain
{

	public static void main(String[] args)
	{
		Webshop webshop = new Webshop();

		Product computer = new Product("New Apple Computer", "Great computer for webdevelopers, where blablla", 2000, 12000, "Computing", "Electronics");
		Product shoe = new Product("Gummistovel", "Great gummistovel from Dolce Gabbana", 100, 600, "Clothes", "Womens");
		User andrea = new User("andreaberglund@hotmail.com", "password", "Andrea", "Berglund", "Sveavägen 49", "14656", "Stockholm");

		

	}

}
