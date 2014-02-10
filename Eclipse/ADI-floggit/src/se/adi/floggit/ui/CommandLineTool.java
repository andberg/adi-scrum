package se.adi.floggit.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import se.adi.floggit.classes.Product;
import se.adi.floggit.webshop.Webshop;

public final class CommandLineTool
{
	private static Webshop webshop = new Webshop();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args)
	{
		// createCategory();
		createProduct();

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

		String input = sc.nextLine();
		List<String> categories = new ArrayList<>();

		while (input.equals("exit"))
		{
			System.out.println("You cannot begin with writing exit");
			input = sc.nextLine();
		}

		while (!input.equals("exit"))
		{
			categories.add(input);
			input = sc.nextLine();
		}

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
	
	private static void createUser(){
		
	}
}
