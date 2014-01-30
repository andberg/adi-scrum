package se.adi.floggit.ui;

import se.adi.floggit.api.CartRepositoryInDB;
import se.adi.floggit.api.CategoryRepositoryInDB;
import se.adi.floggit.api.ProductRepositoryInDB;
import se.adi.floggit.api.UserRepositoryInDB;
import se.adi.floggit.webshop.Webshop;

public class CategoryTest
{
	private static Webshop webshop = new Webshop();

	public static void main(String[] args)
	{
		runDeleteTests();
	}

	private static void runDeleteTests()
	{
		if (webshop.updateCategory("FilmSSS", "Eve", "White")) {
			System.out.println("Category films was updated!");
		} else {
			System.out.println("Category filmSSS was not updated, because it was not found in the database!");
		}
	}
}
