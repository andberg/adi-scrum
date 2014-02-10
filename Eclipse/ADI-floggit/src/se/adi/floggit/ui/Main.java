package se.adi.floggit.ui;

import java.util.Map;

import se.adi.floggit.classes.Product;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public class Main
{
	private static Webshop webshop = new Webshop();

	public static void main(String[] args)
	{
		showProductAPI();
//		showUserAPI();
//		showCartAPI();
//		showCategoryAPI();		
	}

	private static void showCategoryAPI()
	{
		System.out.println(webshop.readAllCategories()); 
		System.out.println();
		
		if(webshop.createCategory("Fotboll", "Sybill", "Le")){
			System.out.println("Category created!");
		} else {
			System.out.println("Category NOT created");
		}
		System.out.println();
		System.out.println(webshop.readAllCategories()); 
	}

	private static void showCartAPI()
	{
		String email = "lorem.vehicula.et@rutrumjusto.edu";
		Map<Product, Integer> cart = webshop.readCart(email);
		System.out.println("Reading cart for " + email + ":");
		for (Product product : cart.keySet()) {
			System.out.println(product.getId() + " - " + product.getName() + " - " + product.getCost() + "kr - " + cart.get(product));
		}
	}

	private static void showUserAPI()
	{
		User user = new User("pelle@hot.com", "Pass", "Pelle", "Pell", "Pelgata", "12312", "Sthlm", "08-0000000");
		if (webshop.createUser(user))
		{
			System.out.println(user + "\nwas created");
		}
		else
		{
			System.out.println("create Fail!");
		}
		
		if (webshop.updateUser("pelle@hot.com", new User("pelle@hot.com", "Pss", "Putte", "Pell", "Pelgata", "12312", "Sthlm", "08-0000000")))
		{
			System.out.println("was updated");
		}
		else
		{
			System.out.println("update Fail!");
		}

		if (webshop.login("pelle@hot.com", "Pss"))
		{
			System.out.println("was logged in");
		}
		else
		{
			System.out.println("Login Fail!");
		}

		if (webshop.deleteUser("pelle@hot.com"))
		{
			System.out.println("was deleted");
		}
		else
		{
			System.out.println("delete Fail!");
		}
	}

	private static void showProductAPI()
	{
		Product car = new Product("Volvo", "Zlatans nya volvo", 50000, 56999393, "Sports & Outdoors", "Mens");
		if (webshop.createProduct(car))
		{
			System.out.println(car.toString() + "\nWere created!");
		}
		else
		{
			System.out.println("Failed to create " + car);
		}

		System.out.println();

		if (webshop.deleteProduct(webshop.readProduct("Volvo").get(0).getId()))
		{
			System.out.println("Volvo has been Deleted. ");
		}
		else
		{
			System.out.println("Unable to delete Volvo");
		}
	}
}
