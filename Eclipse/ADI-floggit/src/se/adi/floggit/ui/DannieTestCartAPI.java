package se.adi.floggit.ui;

import java.util.Map;

import se.adi.floggit.api.Response;
import se.adi.floggit.api.ResponseType;
import se.adi.floggit.classes.Product;
import se.adi.floggit.webshop.Webshop;

public class DannieTestCartAPI
{
	private static final Webshop webshop = new Webshop();
	private static final String email = "danniehakan@hotmail.com";

	public static void main(String[] args)
	{
//		updateCart();
//		readCart();
		deleteFromCart();
		readCart();
	}

	private static void readCart()
	{

		Response<Map<Product, Integer>> response = webshop.readCart(email);

		if (response.getResponse() == ResponseType.SERVER_CONNECTION_SUCCESSFUL)
		{
			System.out.println("Reading cart for user " + email);

			for (Product product : response.getObject().keySet())
			{
				System.out.println(product.toString() + response.getObject().get(product));
			}
		}
		else if (response.getResponse() == ResponseType.USER_CART_EMPTY)
		{
			System.out.println("No cart for user " + email + " was found");
		}
		else if (response.getResponse() == ResponseType.USER_NOT_FOUND)
		{
			System.out.println("Email was not bound to a user in DB");
		}
		else
		{
			System.out.println("Couldn't read cart because of server and DB communication failures");
		}
	}
	
	private static void updateCart() {
				
		ResponseType response = webshop.updateCart(email, 9, 100);
		
		if (response == ResponseType.USER_CART_UPDATED) {
			System.out.println("User cart was updated successfully");
		} else if (response == ResponseType.USER_NOT_FOUND) {
			System.out.println("User email was not found in DB");
		} else if (response == ResponseType.PRODUCT_NOT_FOUND) {
			System.out.println("Product to be added to user cart was not found in DB");
		} else {
			System.out.println("Failed to add product to cart because of server connection issues");
		}
	}
	
	private static void deleteFromCart() {
		
		ResponseType response = webshop.deleteFromCart(email, 10);
		
		if (response == ResponseType.USER_CART_UPDATED) {
			System.out.println("User cart record was successfully deleted");
		} else if (response == ResponseType.USER_NOT_FOUND) {
			System.out.println("Email was not found");
		} else if (response == ResponseType.PRODUCT_NOT_FOUND) {
			System.out.println("User cart record was not deleted because of product not found in cart");
		} else {
			System.out.println("Could not delete from cart because of server connection issues");
		}
	}
}
