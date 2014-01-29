package se.adi.floggit.webshop;

import java.util.List;

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

	public Webshop(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository, CategoryRepository categoryRepository)
	{
		this.userRepository = userRepository;
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public boolean createUser(String string, User user)
	{
		return userRepository.createUser(user);
	}

	public List<User> readAllUsers()
	{
		return userRepository.readAllUsers();
	}

	public boolean updateUser(String email, User user)
	{
		return userRepository.updateUser(email, user);
	}

	public boolean deleteUser(String email)
	{
		return userRepository.deleteUser(email);
	}

	public boolean login(String email, String password)
	{
		return userRepository.login(email, password);
	}

	public List<Product> readCart(String email)
	{
		return cartRepository.readCart(email);
	}

	public boolean updateCart(String email, String productName, int quantity)
	{
		return cartRepository.updateCart(email, productName, quantity);
	}

	public boolean deleteFromCart(String email, String productName)
	{
		return cartRepository.deleteFromCart(email, productName);
	}

	public boolean createProduct(Product product)
	{
		return productRepository.createProduct(product);
	}

	public List<String> readProductsInCategory(String categoryName)
	{
		return productRepository.readProductsInCategory(categoryName);
	}

	public List<Product> readAllProducts()
	{
		return productRepository.readAllProducts();
	}

	public boolean updateProduct(int id, Product product)
	{
		return productRepository.updateProduct(id, product);
	}

	public boolean deleteProduct(int id)
	{
		return productRepository.deleteProduct(id);
	}

	public boolean createCategory(String categoryName, String staffFirstname, String staffSurname)
	{
		return false;
	}

	public List<String> readAllCategories()
	{
		return categoryRepository.readAllCategories();
	}

	public boolean updateCategory(String categoryName, String staffFirstname, String staffSurname)
	{
		return categoryRepository.updateCategory(categoryName, staffFirstname, staffSurname);
	}

	public boolean deleteCategory(String categoryName)
	{
		return categoryRepository.deleteCategory(categoryName);
	}
}
