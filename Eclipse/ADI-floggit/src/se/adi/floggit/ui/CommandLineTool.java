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
		createUser();
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
				System.out.println("You have to input a number");
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
				System.out.println("You have to input a number");
			}
		}

		System.out.println("Product categories, please write one at a time and press enter. When done write exit and enter");

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
				System.out.println("You need at least one category");
			}
			else
			{
				categories.add(input);
			}
		}
		System.out.println("Terminated");

		Product product = new Product(productName, productDescription, cost, rrp, categories);
		if (webshop.createProduct(product))
		{
			System.out.println("Product created");
		}
		else
		{
			System.out.println("Error! One or more categories does not exist in DB");
		}
	}

	private static void createUser()
	{
		System.out.println("Enter Username/E-mail");
		String email = sc.nextLine();

		System.out.println("Enter password");
		String password = sc.nextLine();

		System.out.println("Enter firstname:");
		String firstname = sc.nextLine();

		System.out.println("Enter surname");
		String surname = sc.nextLine();

		System.out.println("Enter street address:");
		String streetAddress = sc.nextLine();

		System.out.println("Enter postcode:");
		String postcode = sc.nextLine();

		System.out.println("Enter town:");
		String town = sc.nextLine();

		System.out.println("Enter phonenumber");
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

	public static void updateCategory()
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
}
