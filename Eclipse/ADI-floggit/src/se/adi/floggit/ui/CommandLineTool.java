package se.adi.floggit.ui;

import java.util.List;
import java.util.Scanner;

import se.adi.floggit.classes.Product;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public final class CommandLineTool
{
	private static final String MENU_TEXT = "\nOPTIONS MENU\n1. Create category\n"
			+ "2. Create product\n"
			+ "3. Create user\n"
			+ "4. Update category\n"
			+ "5. Update product\n"
			+ "6. Update user\n"
			+ "7. Login user\n"
			+ "8. List products by category\n"
			+ "9. Search product by name\n"
			+ "0. Close down system\n\n"
			+ "-- Test functionality --\n"
			+ "10. Read all categories\n"
			+ "11. Read all products\n"
			+ "12. Read all users";

	private static Webshop webshop = new Webshop();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args)
	{
		initiateMenu();
	}

	private static void initiateMenu()
	{
		int choice = -1;

		while ((choice < 0) || (choice > 12))
		{
			try
			{
				System.out.println(MENU_TEXT);
				choice = Integer.parseInt(scanner.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("You must input a number from 0 to 12");
			}
		}

		switch (choice)
		{
		case 1:
			createCategory();
			break;
		case 2:
			createProduct();
			break;
		case 3:
			createUser();
			break;
		case 4:
			updateCategory();
			break;
		case 5:
			updateProduct();
			break;
		case 6:
			updateUser();
			break;
		case 7:
			loginUser();
			break;
		case 8:
			listProductsByCategory();
			break;
		case 9:
			searchProductByName();
			break;
		case 10:
			readAllCategories();
			break;
		case 11:
			readAllProducts();
			break;
		case 12:
			readAllUsers();
			break;
		case 0:
			System.out.println("System was shut down");
			System.exit(0);
		}
	}

	private static void createCategory()
	{
		System.out.println("Creation of category prompted\n\nCategory name:");
		String categoryName = scanner.nextLine();

		while (!categoryName.equals("menu"))
		{
			System.out.println("Responsible staff firstname:");
			String staffFirstname = scanner.nextLine();
			System.out.println("Responsible staff surname:");
			String staffSurname = scanner.nextLine();

			if (webshop.createCategory(categoryName, staffFirstname, staffSurname))
			{
				System.out.println("Category " + categoryName + " was created with "
						+ staffFirstname + " " + staffSurname + " assigned as responsible staff");
			}
			else
			{
				System.out.println("Category was not created because of it already being registered"
						+ ", or the staff member was not found");
			}
			System.out.println("\nCategory name:");
			categoryName = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void createProduct()
	{
		int numOfCategories = 0;
		double cost = 0;
		double rrp = 0;
		System.out.println("Creation of product prompted\n\nProduct name:");
		String name = scanner.nextLine();

		while (!name.equals("menu"))
		{
			System.out.println("Description:");
			String description = scanner.nextLine();
			System.out.println("Cost:");
			do
			{
				try
				{
					cost = Double.parseDouble(scanner.nextLine());
					break;
				}
				catch (NumberFormatException e)
				{
					System.out.println("You have to input a number\n\nCost:");
					continue;
				}
			}
			while (true);
			System.out.println("RRP:");
			do
			{
				try
				{
					rrp = Double.parseDouble(scanner.nextLine());
					break;
				}
				catch (NumberFormatException e)
				{
					System.out.println("You have to input a number\n\nRRP:");
					continue;
				}
			}
			while (true);
			do
			{
				try
				{
					System.out.println("Number of categories to be added:");
					numOfCategories = Integer.parseInt(scanner.nextLine());
					if (numOfCategories < 1)
					{
						System.out.println("You cannot have a product with " + numOfCategories + " amount of categories");
						continue;
					}
					break;
				}
				catch (NumberFormatException e)
				{
					System.out.println("You have to input a number\n\n");
					continue;
				}
			}
			while (true);
			String[] categories = new String[numOfCategories];
			for (int i = 0; i < numOfCategories; i++)
			{
				System.out.println("Category " + (i + 1) + ":");
				categories[i] = scanner.nextLine();
			}
			Product product = new Product(name, description, cost, rrp, categories);
			if (webshop.createProduct(product))
			{
				System.out.println("Product was successfully created");
			}
			else
			{
				System.out.println("Product was not created successfully because of one or more categories not found in the register");
			}
			System.out.println("\nProduct name:");
			name = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void createUser()
	{
		System.out.println("Creation of user prompted\n\nUsername:");
		String username = scanner.nextLine();

		while (!username.equals("menu"))
		{
			System.out.println("Password:");
			String password = scanner.nextLine();
			System.out.println("Firstname:");
			String firstname = scanner.nextLine();
			System.out.println("Surname:");
			String surname = scanner.nextLine();
			System.out.println("Street address:");
			String streetAddress = scanner.nextLine();
			System.out.println("Postcode:");
			String postcode = scanner.nextLine();
			System.out.println("Town:");
			String town = scanner.nextLine();
			System.out.println("Phonenumber(can be left empty):");
			String phonenumber = scanner.nextLine();
			
			User user = null;
			if (phonenumber.equals("")) {
				user = new User(username, password, firstname, surname, streetAddress, postcode, town);
			} else {
				user = new User(username, password, firstname, surname, streetAddress, postcode, town, phonenumber);
			}
			
			if (webshop.createUser(user)) {
				System.out.println("User was created successfully");
			} else {
				System.out.println("User was not created because of already registered username");
			}
			
			System.out.println("\nUsername:");
			username = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void updateCategory()
	{
		System.out.println("Updating of category prompted\n\nName of category to be updated:");
		String categoryName = scanner.nextLine();

		while (!categoryName.equals("menu"))
		{
			System.out.println("New responsble staff firstname:");
			String staffFirstname = scanner.nextLine();
			System.out.println("New responsible staff surname:");
			String staffSurname = scanner.nextLine();

			if (webshop.updateCategory(categoryName, staffFirstname, staffSurname))
			{
				System.out.println("Category " + categoryName + " was updated with "
						+ staffFirstname + " " + staffSurname + " assigned as responsible staff");
			}
			else
			{
				System.out.println("Category was not updated because of it not being found in the system"
						+ ", or the new staff to be assigned was not found");
			}
			System.out.println("\nName of category to be updated:");
			categoryName = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void updateProduct()
	{
		int numOfCategories = 0;
		int id = 0;
		double cost = 0;
		double rrp = 0;
		
		System.out.println("Updating of product prompted\n\nNew product name:");
		String name = scanner.nextLine();

		while (!name.equals("menu"))
		{
			System.out.println("New description:");
			String description = scanner.nextLine();
			System.out.println("New cost:");
			do
			{
				try
				{
					cost = Double.parseDouble(scanner.nextLine());
					break;
				}
				catch (NumberFormatException e)
				{
					System.out.println("You have to input a number\n\nCost:");
					continue;
				}
			}
			while (true);
			do
			{
				try
				{
					System.out.println("New RRP:");
					rrp = Double.parseDouble(scanner.nextLine());
					break;
				}
				catch (NumberFormatException e)
				{
					System.out.println("You have to input a number\n\n");
					continue;
				}
			}
			while (true);
			do
			{
				try
				{
					System.out.println("Number of categories to be added:");
					numOfCategories = Integer.parseInt(scanner.nextLine());
					if (numOfCategories < 1)
					{
						System.out.println("You cannot have a product with " + numOfCategories + " amount of categories\n\n");
						continue;
					}
					break;
				}
				catch (NumberFormatException e)
				{
					System.out.println("You have to input a number\n\n");
					continue;
				}
			} while(true);
			String[] categories = new String[numOfCategories];
			for (int i = 0; i < numOfCategories; i++)
			{
				System.out.println("Category " + (i + 1) + ":");
				categories[i] = scanner.nextLine();
			}
			do
			{
				try
				{
					System.out.println("Id of product to be changed:");
					id = Integer.parseInt(scanner.nextLine());
					break;
				}
				catch (NumberFormatException e)
				{
					System.out.println("You have to input a number\n\n");
					continue;
				}
			}
			while (true);
			Product product = new Product(name, description, cost, rrp, categories);
			if (webshop.updateProduct(id, product))
			{
				System.out.println("Product was successfully updated");
			}
			else
			{
				System.out.println("Product was not updated successfully because of one or more categories not found in the register, or because of invalid product id");
			}
			System.out.println("\nNew product name:");
			name = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void updateUser()
	{
		System.out.println("Updating of user prompted\n\nNew username:");
		String username = scanner.nextLine();

		while (!username.equals("menu"))
		{
			System.out.println("New password:");
			String password = scanner.nextLine();
			System.out.println("New firstname:");
			String firstname = scanner.nextLine();
			System.out.println("New surname:");
			String surname = scanner.nextLine();
			System.out.println("New street address:");
			String streetAddress = scanner.nextLine();
			System.out.println("New postcode:");
			String postcode = scanner.nextLine();
			System.out.println("New town:");
			String town = scanner.nextLine();
			System.out.println("New phonenumber(can be left empty):");
			String phonenumber = scanner.nextLine();
			
			User user = null;
			if (phonenumber.equals("")) {
				user = new User(username, password, firstname, surname, streetAddress, postcode, town);
			} else {
				user = new User(username, password, firstname, surname, streetAddress, postcode, town, phonenumber);
			}
			
			System.out.println("Username of person to update");
			String userToBeChangedUsername = scanner.nextLine();
			
			if (webshop.updateUser(userToBeChangedUsername, user)) {
				System.out.println("User was updated successfully");
			} else {
				System.out.println("User was not updated because of already registered username, "
						+ "or because of username to the user to be changed not being found in the register");
			}
			
			System.out.println("\nNew username:");
			username = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void loginUser()
	{
		System.out.println("Login prompted\n\nUsername:");
		String username = scanner.nextLine();

		while (!username.equals("menu"))
		{
			System.out.println("Password:");
			String password = scanner.nextLine();

			if (webshop.login(username, password))
			{
				System.out.println(username + " was logged in successfully");
			}
			else
			{
				System.out.println("Username or password was incorrect");
			}
			System.out.println("\nUsername:");
			username = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void listProductsByCategory()
	{
		System.out.println("Product listing by category prompted\n\nCategory name:");
		String categoryName = scanner.nextLine();

		while (!categoryName.equals("menu"))
		{
			List<String> productNames = webshop.readProductsInCategory(categoryName);
			if (productNames.size() == 0)
			{
				System.out.println("No products attached to specified category, or the category was not found registered");
			}
			else
			{
				System.out.println("Listing products:\n");
				for (String productName : productNames)
				{
					System.out.println(productName);
				}
			}
			System.out.println("\nCategory name:");
			categoryName = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void searchProductByName()
	{
		System.out.println("Product search by name prompted\n\nProduct name:");
		String productName = scanner.nextLine();

		while (!productName.equals("menu"))
		{
			List<Product> products = webshop.readProduct(productName);
			if (products.size() == 0)
			{
				System.out.println("No products with that name was found\n");
			}
			else
			{
				System.out.println("Listing products:\n");
				for (Product product : products)
				{
					System.out.println(product);
				}
			}
			System.out.println("Product name:");
			productName = scanner.nextLine();
		}
		initiateMenu();
	}

	private static void readAllCategories()
	{
		System.out.println(webshop.readAllCategories());
		initiateMenu();
	}

	private static void readAllProducts()
	{
		for (Product product : webshop.readAllProducts()) {
			System.out.println(product);
		}
		initiateMenu();
	}

	private static void readAllUsers()
	{
		for (User user : webshop.readAllUsers()) {
			System.out.println(user);
		}
		initiateMenu();
	}
}
