package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.classes.Product;

public interface CartRepository {
	List<Product> readCart(String email);
	boolean updateCart(String email, String productName);
	String deleteFromCart(String email, String productName);
}
