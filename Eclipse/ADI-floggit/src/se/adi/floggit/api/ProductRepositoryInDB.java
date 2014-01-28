package se.adi.floggit.api;

import java.util.List;

import se.adi.floggit.classes.Product;
import se.adi.floggit.interfaces.ProductRepository;

public final class ProductRepositoryInDB implements ProductRepository {

	@Override
	public boolean createProduct(Product product) {
		return false;
	}
	
	@Override
	public List<Product> readProductsInCategory(String categoryName) {
		return null;
	}
	
	@Override
	public List<Product> readAllProducts() {
		return null;
	}

	@Override
	public boolean updateProduct(String productName) {
		return false;
	}

	@Override
	public boolean deleteProduct(int id) {
		return false;
	}
}
