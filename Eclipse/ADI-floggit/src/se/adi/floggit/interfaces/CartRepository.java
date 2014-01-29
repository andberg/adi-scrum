package se.adi.floggit.interfaces;

import java.util.Map;

import se.adi.floggit.classes.Product;

public interface CartRepository {
	Map<Product, Integer> readCart(String email);
	boolean updateCart(String email, int productId, int quantity);
	boolean deleteFromCart(String email, int productId);
}
