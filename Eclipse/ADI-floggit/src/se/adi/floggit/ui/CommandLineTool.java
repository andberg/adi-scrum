package se.adi.floggit.ui;

import java.util.List;
import java.util.Scanner;

import se.adi.floggit.classes.Product;
import se.adi.floggit.webshop.Webshop;

public final class CommandLineTool
{
	private static final String MENU_TEXT = "\nOPTIONS MENU\n1. Create category\n"
			+ "2. Create product\n"
			+ "3. Create user\n"
			+ "4. Edit category\n"
			+ "5. Edit product\n"
			+ "6. Edit user\n"
			+ "7. Login user\n"
			+ "8. List products by category\n"
			+ "9. Search product by name\n0. Close down system";

	private static Webshop webshop = new Webshop();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args)
	{
		initiateMenu();
	}

	private static void initiateMenu()
	{
		int choice = -1;

		while ((choice < 0)||(choice > 9))
		{
			try
			{
				System.out.println(MENU_TEXT);
				choice = Integer.parseInt(scanner.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("You must input a number from 0 to 9");
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
			editCategory();
			break;
		case 5:
			editProduct();
			break;
		case 6:
			editUser();
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
		case 0:
			System.out.println("System was shut down");
			System.exit(0);
		default:
		}
	}
	
	private static void createCategory()
	{
		
	}

	private static void createProduct()
	{
		
	}
	
	private static void createUser()
	{
		
	}

	private static void editCategory()
	{
		
	}

	private static void editProduct()
	{
		
	}

	private static void editUser()
	{
		
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
				System.out.println("No products attached to specified category, or the category was not registered\n");
			} else {
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
		
		while (!productName.equals("menu")) {
			List<Product> products = webshop.readProduct(productName);
			if (products.size() == 0) {
				System.out.println("No products with that name was found\n");
			} else {
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
}
