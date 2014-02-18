package se.adi.floggit.webshop;

import java.util.List;
import java.util.Map;

import se.adi.floggit.api.CartRepositoryInDB;
import se.adi.floggit.api.CategoryRepositoryInDB;
import se.adi.floggit.api.ProductRepositoryInDB;
import se.adi.floggit.api.Response;
import se.adi.floggit.api.ResponseType;
import se.adi.floggit.api.UserRepositoryInDB;
import se.adi.floggit.classes.Product;
import se.adi.floggit.classes.User;
import se.adi.floggit.interfaces.CartRepository;
import se.adi.floggit.interfaces.CategoryRepository;
import se.adi.floggit.interfaces.ProductRepository;
import se.adi.floggit.interfaces.UserRepository;

public final class Webshop
{
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public Webshop()
	{
		this.userRepository = new UserRepositoryInDB();
		this.cartRepository = new CartRepositoryInDB();
		this.productRepository = new ProductRepositoryInDB();
		this.categoryRepository = new CategoryRepositoryInDB();
	}

	public ResponseType createUser(User user)
	{
		return userRepository.createUser(user);
	}

	public Response<List<User>> readAllUsers()
	{
		return userRepository.readAllUsers();
	}

	public ResponseType updateUser(String email, User user)
	{
		return userRepository.updateUser(email, user);
	}

	public ResponseType deleteUser(String email)
	{
		return userRepository.deleteUser(email);
	}

	public ResponseType login(String email, String password)
	{
		return userRepository.login(email, password);
	}

	public Response<Map<Product, Integer>> readCart(String email)
	{
		return cartRepository.readCart(email);
	}

	public ResponseType updateCart(String email, int productId, int quantity)
	{
		return cartRepository.updateCart(email, productId, quantity);
	}

	public ResponseType deleteFromCart(String email, int productId)
	{
		return cartRepository.deleteFromCart(email, productId);
	}

	public ResponseType createProduct(Product product)
	{
		return productRepository.createProduct(product);
	}

	public Response<List<String>> readProductsInCategory(String categoryName)
	{
		return productRepository.readProductsInCategory(categoryName);
	}

	public Response<List<Product>> readProduct(String productName)
	{
		return productRepository.readProduct(productName);
	}

	public Response<List<Product>> readAllProducts()
	{
		return productRepository.readAllProducts();
	}

	public ResponseType updateProduct(int id, Product product)
	{
		return productRepository.updateProduct(id, product);
	}

	public ResponseType deleteProduct(int id)
	{
		return productRepository.deleteProduct(id);
	}

	public ResponseType createCategory(String categoryName,
			String staffFirstname, String staffSurname)
	{
		return categoryRepository.createCategory(categoryName, staffFirstname,
				staffSurname);
	}

	public Response<List<String>> readAllCategories()
	{
		return categoryRepository.readAllCategories();
	}

	public ResponseType updateCategory(String categoryName,
			String staffFirstname, String staffSurname)
	{
		return categoryRepository.updateCategory(categoryName, staffFirstname,
				staffSurname);
	}

	public ResponseType deleteCategory(String categoryName)
	{
		return categoryRepository.deleteCategory(categoryName);
	}
}