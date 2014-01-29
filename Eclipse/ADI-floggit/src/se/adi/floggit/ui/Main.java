package se.adi.floggit.ui;

import se.adi.floggit.api.CartRepositoryInDB;
import se.adi.floggit.api.CategoryRepositoryInDB;
import se.adi.floggit.api.ProductRepositoryInDB;
import se.adi.floggit.api.UserRepositoryInDB;
import se.adi.floggit.classes.Product;
import se.adi.floggit.webshop.Webshop;

public class Main
{
	public static void main(String[] args)
	{

		Webshop webshop = new Webshop(new UserRepositoryInDB(), new CartRepositoryInDB(),
				new ProductRepositoryInDB(), new CategoryRepositoryInDB());
		// User isak = new User("dannie@hotmail.com", "secret", "Dannie",
		// "Håkansson", "Plommonvägen 4", "263 65", "Viken");
		Product gamingDesktopComputer = new Product("Monster Gaming Desktop Computer", "Great new computer from Apple", 2000, 12000, "ToysSss");
		Product macBookOther = new Product("Computer hej", "Great old computer from Apple", 1000, 9000, "Computing", "Electronics");
		
		
		webshop.updateProduct(23, gamingDesktopComputer); 
//		webshop.createProduct(macBookNew); 
//		webshop.deleteProduct(17);
	
		for(Product product : webshop.readAllProducts()){
			System.out.println(product);
		}
	}
}
