package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.classes.Product;

public interface ProductRepository
{
	boolean createProduct(Product product);

	List<String> readProductsInCategory(String categoryName);
	
	List<Product> readProduct(String productName);

	List<Product> readAllProducts();

	boolean updateProduct(int id, Product product);

	boolean deleteProduct(int id);
}
