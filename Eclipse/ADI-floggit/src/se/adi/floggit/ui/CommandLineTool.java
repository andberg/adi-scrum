package se.adi.floggit.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import se.adi.floggit.classes.Product;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public final class CommandLineTool
{
	private static Webshop webshop = new Webshop();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args)
	{
		// createCategory();
		// createProduct();
		// updateCategory();
		// validateUser();
//		searchProductByName();
//		listProductsByCategory();
		// createUser();
//		 updateUser();
	}

	private static void createCategory()
	{

		System.out.println("Category name:");
		String categoryName = sc.nextLine();

		System.out.println("Responsible staff fistname:");
		String staffFirstname = sc.nextLine();

		System.out.println("Responsible staff surname:");
		String staffSurname = sc.nextLine();

		if (webshop.createCategory(categoryName, staffFirstname, staffSurname))
		{
			System.out.println("Category created in DB!");
		}
		else
		{
			System.out.println("Error! \nControl that the Category does not already exist in DB \nand/or that staff assigned was not registered in the DB. ");
		}
	}

	private static void createProduct()
	{
		System.out.println("Product name:");
		String productName = sc.nextLine();

		System.out.println("Product description: ");
		String productDescription = sc.nextLine();

		double cost;
		while (true)
		{
			try
			{
				System.out.println("Product cost:");
				cost = Double.parseDouble(sc.nextLine());
				break;
			}
			catch (NumberFormatException e)
			{
				System.out.println("You have to input a number.");
			}
		}

		double rrp;
		while (true)
		{
			try
			{
				System.out.println("Product rrp:");
				rrp = Double.parseDouble(sc.nextLine());
				break;
			}
			catch (NumberFormatException e)
			{
				System.out.println("You have to input a number.");
			}
		}

		System.out.println("Product categories, please write one at a time and press enter. When done press enter twice.");

		List<String> categories = new ArrayList<>();

		while (true)
		{

			String input = sc.nextLine();
			if (input.equals(""))
			{
				if (!categories.isEmpty())
				{
					break;
				}
				System.out.println("You need at least one category!");
			}
			else
			{
				categories.add(input);
			}
		}

		Product product = new Product(productName, productDescription, cost, rrp, categories);
		if (webshop.createProduct(product))
		{
			System.out.println("Product created.");
		}
		else
		{
			System.out.println("Error! One or more categories does not exist in DB.");
		}
	}

	private static void createUser()
	{
		System.out.println("Enter Username/E-mail:");
		String email = sc.nextLine();

		System.out.println("Enter password:");
		String password = sc.nextLine();

		System.out.println("Enter firstname:");
		String firstname = sc.nextLine();

		System.out.println("Enter surname:");
		String surname = sc.nextLine();

		System.out.println("Enter street address:");
		String streetAddress = sc.nextLine();

		System.out.println("Enter postcode:");
		String postcode = sc.nextLine();

		System.out.println("Enter town:");
		String town = sc.nextLine();

		System.out.println("Enter phonenumber:");
		String phonenumber = sc.nextLine();

		User user = null;
		if (phonenumber.equals(""))
		{
			user = new User(email, password, firstname, surname, streetAddress, postcode, town);
		}
		else
		{
			user = new User(email, password, firstname, surname, streetAddress, postcode, town, phonenumber);
		}

		if (webshop.createUser(user))
		{
			System.out.println("User was created in DB!");
		}
		else
		{
			System.out.println("Error! Username/Email already in DB.");
		}
	}

	private static void updateCategory()
	{
		System.out.println("Category name:");
		String categoryName = sc.nextLine();

		System.out.println("New responsible staff fistname:");
		String staffFirstname = sc.nextLine();

		System.out.println("New responsible staff surname:");
		String staffSurname = sc.nextLine();

		if (webshop.updateCategory(categoryName, staffFirstname, staffSurname))
		{
			System.out.println("Category updated in DB with " + staffFirstname + " " + staffSurname + " assigned as responsible staff");
		}
		else
		{
			System.out.println("Error! Category " + categoryName + " was not found in DB, or staff member " + staffFirstname + " " + staffSurname
					+ " to be assigned responsible was not found in DB");
		}
	}

	private static void validateUser()
	{
		System.out.println("Username:");
		String username = sc.nextLine();

		System.out.println("Password:");
		String password = sc.nextLine();

		if (webshop.login(username, password))
		{
			System.out.println(username + " was successfully logged in");
		}
		else
		{
			System.out.println("Login failed because of username not registered in DB, and/or password incorrect");
		}
	}

	private static void searchProductByName()
	{
		System.out.println("Product name:");
		String productName = sc.nextLine();

		List<Product> products = webshop.readProduct(productName);

		if (products.size() == 0)
		{
			System.out.println("No products with name " + productName + " was found in DB");
		}
		else
		{
			for (Product product : products)
			{
				System.out.println(product);
			}
		}
	}

	private static void listProductsByCategory()
	{
		System.out.println("Category name:");
		String categoryName = sc.nextLine();

		List<String> products = webshop.readProductsInCategory(categoryName);

		if (products.size() == 0)
		{
			System.out.println("No products in category " + categoryName + " was found in DB, or the category was not found in DB");
		}
		else
		{
			for (String string : products)
			{
				System.out.println(string);
			}
		}
	}

	private static void updateUser()
	{
		System.out.println("Which user would you like to update? Enter valid email.");
		String emailID = sc.nextLine();

		System.out.println("Enter new Username/E-mail:");
		String email = sc.nextLine();

		System.out.println("Enter password:");
		String password = sc.nextLine();

		System.out.println("Enter firstname:");
		String firstname = sc.nextLine();

		System.out.println("Enter surname:");
		String surname = sc.nextLine();

		System.out.println("Enter street address:");
		String streetAddress = sc.nextLine();

		System.out.println("Enter postcode:");
		String postcode = sc.nextLine();

		System.out.println("Enter town:");
		String town = sc.nextLine();

		System.out.println("Enter phonenumber:");
		String phonenumber = sc.nextLine();

		User user = null;
		if (phonenumber.equals(""))
		{
			user = new User(email, password, firstname, surname, streetAddress, postcode, town);
		}
		else
		{
			user = new User(email, password, firstname, surname, streetAddress, postcode, town, phonenumber);
		}

		if (webshop.updateUser(emailID, user))
		{
			System.out.println("User was updated in DB!");
		}
		else
		{
			System.out.println("Error! Update failure, check if given email is valid " + emailID);
		}

	}
}
