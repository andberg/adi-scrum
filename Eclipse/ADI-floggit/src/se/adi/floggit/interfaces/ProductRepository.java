package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.classes.Product;

public interface ProductRepository {
	Product createProduct(Product product);
	List<Product> readProduct(String productName);
	List<Product> readAllProducts();
	Product updateProduct(String productName);
	Product deleteProduct(int id);
}
