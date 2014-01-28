package se.adi.floggit.ui;

import se.adi.floggit.api.CartRepositoryInDB;
import se.adi.floggit.api.CategoryRepositoryInDB;
import se.adi.floggit.api.ProductRepositoryInDB;
import se.adi.floggit.api.UserRepositoryInDB;
import se.adi.floggit.classes.Product;
import se.adi.floggit.webshop.Webshop;

public class Main {
	public static void main(String[] args) {

		Webshop webshop = new Webshop(new UserRepositoryInDB(), new CartRepositoryInDB(),
				new ProductRepositoryInDB(), new CategoryRepositoryInDB());
		//User isak = new User("dannie@hotmail.com", "secret", "Dannie", "Håkansson", "Plommonvägen 4", "263 65", "Viken");
		Product macBookNew = new Product("Computer", "Great new computer from Apple", 2000, 12000, "Computing", "Electronics"); 
		
		webshop.readAllProducts(); 
			
	
	}
}
