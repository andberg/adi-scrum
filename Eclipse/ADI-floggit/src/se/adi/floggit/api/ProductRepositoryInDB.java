package se.adi.floggit.api;

import java.util.List;

import se.adi.floggit.classes.Product;
import se.adi.floggit.interfaces.ProductRepository;

public class ProductRepositoryInDB implements ProductRepository {

	@Override
	public Product createProduct(Product product) {
		return null;
	}

	@Override
	public List<Product> readProduct(String productName) {
		return null;
	}
	
	@Override
	public List<Product> readAllProducts() {
		return null;
	}

	@Override
	public Product updateProduct(String productName) {
		return null;
	}

	@Override
	public Product deleteProduct(int id) {
		return null;
	}
}
