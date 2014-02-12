package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.api.Response;
import se.adi.floggit.api.ResponseType;
import se.adi.floggit.classes.Product;

public interface ProductRepository
{
	ResponseType createProduct(Product product);

	Response<List<String>> readProductsInCategory(String categoryName);
	
	Response<List<Product>> readProduct(String productName);

	Response<List<Product>> readAllProducts();

	ResponseType updateProduct(int id, Product product);

	ResponseType deleteProduct(int id);
}
