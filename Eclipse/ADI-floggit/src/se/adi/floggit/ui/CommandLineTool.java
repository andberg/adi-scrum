package se.adi.floggit.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import se.adi.floggit.api.Response;
import se.adi.floggit.api.ResponseType;
import se.adi.floggit.classes.Product;
import se.adi.floggit.classes.User;
import se.adi.floggit.webshop.Webshop;

public final class CommandLineTool
{
	private static final String MENU_TEXT =
			"\nOPTIONS MENU\n1. Create category\n"
					+ "2. Create product\n"
					+ "3. Create user\n"
					+ "4. Update category\n"
					+ "5. Update product\n"
					+ "6. Update user\n"
					+ "7. Validate user\n"
					+ "8. List products by category\n"
					+ "9. Search product by name";

	private static final Webshop webshop = new Webshop();
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args)
	{
		while (true)
		{
			initiateMenu();
		}
	}

	private static void initiateMenu()
	{
		int input = -1;
		while (input < 0 || input > 9)
		{
			try
			{
				System.out.println(MENU_TEXT);
				input = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("You have to input number");
			}
		}

		switch (input)
		{
		case 0:
			System.out.println("System shutting down");
			System.exit(0);
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
			validateUser();
			break;
		case 8:
			listProductsByCategory();
			break;
		case 9:
			searchProductByName();
			break;
		}
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
		if (webshop.createProduct(product) == ResponseType.PRODUCT_CREATED)
		{
			System.out.println("Product created.");
		}
		else if (webshop.createProduct(product) == ResponseType.CATEGORY_NOT_FOUND)
		{
			System.out.println("Error! One or more categories does not exist in DB.");
		}
		else
		{
			System.out.println("Create product failed because of connection problems between server and DB");
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

		ResponseType response = webshop.createUser(user);

		if (response == ResponseType.USER_CREATED)
		{
			System.out.println("User was created in DB!");
		}
		else if (response == ResponseType.USER_NOT_CREATED)
		{
			System.out.println("Error! Username/Email was already registered in DB.");
		}
		else
		{
			System.out.println("Creation of user failed because of server and DB communication problems");
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

	private static void updateProduct()
	{
		int productId;
		while (true)
		{
			try
			{
				System.out.println("Product ID:");
				productId = Integer.parseInt(sc.nextLine());
				break;
			}
			catch (NumberFormatException e)
			{
				System.out.println("You have to input a number");
			}
		}

		System.out.println("New product name:");
		String productName = sc.nextLine();

		System.out.println("New product description: ");
		String productDescription = sc.nextLine();

		double cost;
		while (true)
		{
			try
			{
				System.out.println("New product cost:");
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
				System.out.println("New product rrp:");
				rrp = Double.parseDouble(sc.nextLine());
				break;
			}
			catch (NumberFormatException e)
			{
				System.out.println("You have to input a number");
			}
		}

		System.out.println("Product categories, please write one at a time and press enter. When done write press enter again");

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
		System.out.println("Categories " + categories + " added");

		Product product = new Product(productName, productDescription, cost, rrp, categories);
		if (webshop.updateProduct(productId, product) == ResponseType.PRODUCT_UPDATED)
		{
			System.out.println("Product with id: " + productId + " updated");
		}
		else if (webshop.updateProduct(productId, product) == ResponseType.PRODUCT_NOT_FOUND)
		{
			System.out.println("Error! Product ID doesn't exist in DB!");
		}
		else if (webshop.updateProduct(productId, product) == ResponseType.CATEGORY_NOT_FOUND)
		{
			System.out.println("Error! One or more categories does not exist in DB.");
		}
		else
		{
			System.out.println("Update product failed because of connection problems between server and DB");
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

		
		ResponseType response = webshop.updateUser(emailID, user);
		if (response == ResponseType.USER_UPDATED)
		{
			System.out.println("User was updated in DB!");
		}
		else if (response == ResponseType.USER_EMAIL_NOT_FOUND)
		{
			System.out.println("User updating failed because of email not found in DB");
		}
		else if (response == ResponseType.USER_EMAIL_DUPLICATE)
		{
			System.out.println("User updating failed because of new email was already found in DB");
		}
		else
		{
			System.out.println("Error! Updating of user failed because of server and DB communication failure");
		}
	}

	private static void validateUser()
	{
		System.out.println("Username:");
		String username = sc.nextLine();

		System.out.println("Password:");
		String password = sc.nextLine();

		ResponseType response = webshop.login(username, password);

		if (response == ResponseType.LOGIN_SUCCESSFUL)
		{
			System.out.println(username + " was successfully logged in");
		}
		else if (response == ResponseType.LOGIN_FAILED)
		{
			System.out.println("Login failed because of username not registered in DB, and/or password incorrect");
		}
		else
		{
			System.out.println("Login failed because of connection problems between server and DB");
		}
	}

	private static void listProductsByCategory()
	{

		System.out.println("Category name:");
		String categoryName = sc.nextLine();

		Response<List<String>> response = webshop.readProductsInCategory(categoryName);

		List<String> products = response.getObject();

		if (products.size() == 0 && response.getResponse() == ResponseType.SERVER_CONNECTION_SUCCESSFUL)
		{
			System.out.println("No products in category " + categoryName + " was found in DB, or the category was not found in DB");
		}
		else if (products.size() > 0 && response.getResponse() == ResponseType.SERVER_CONNECTION_SUCCESSFUL)
		{
			for (String string : products)
			{
				System.out.println(string);
			}
		}
		else
		{
			System.out.println("Serach failed because of connection problems between server and DB");
		}
	}

	private static void searchProductByName()
	{
		System.out.println("Product name:");
		String productName = sc.nextLine();

		Response<List<Product>> response = webshop.readProduct(productName);

		List<Product> products = response.getObject();

		if (products.size() == 0 && response.getResponse() == ResponseType.SERVER_CONNECTION_SUCCESSFUL)
		{
			System.out.println("No products with name " + productName + " was found in DB");
		}
		else if (products.size() > 0 && response.getResponse() == ResponseType.SERVER_CONNECTION_SUCCESSFUL)
		{
			for (Product product : products)
			{
				System.out.println(product);
			}
		}
		else
		{
			System.out.println("Serach failed because of connection problems between server and DB");

		}
	}
}
