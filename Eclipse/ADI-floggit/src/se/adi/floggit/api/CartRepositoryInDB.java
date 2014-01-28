package se.adi.floggit.api;

import java.util.List;

import se.adi.floggit.classes.Product;
import se.adi.floggit.interfaces.CartRepository;

public final class CartRepositoryInDB implements CartRepository {

	@Override
	public List<Product> readCart(String email) {
		return null;
	}

	@Override
	public boolean updateCart(String email, String productName, int quantity) {
		return false;
	}

	@Override
	public String deleteFromCart(String email, String productName) {
		return null;
	}
}
