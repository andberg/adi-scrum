package se.adi.floggit.interfaces;

import java.util.Map;

import se.adi.floggit.api.Response;
import se.adi.floggit.api.ResponseType;
import se.adi.floggit.classes.Product;

public interface CartRepository {
	Response<Map<Product, Integer>> readCart(String email);

	ResponseType updateCart(String email, int productId, int quantity);

	ResponseType deleteFromCart(String email, int productId);
}
