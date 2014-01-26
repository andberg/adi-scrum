package se.adi.floggit.api;

import java.util.List;

import se.adi.floggit.classes.Product;
import se.adi.floggit.interfaces.CategoryRepository;

public class CategoryRepositoryInDB implements CategoryRepository {
	
	@Override
	public String createCategory(String categoryName, String staffResponsible) {
		return null;
	}

	@Override
	public List<Product> readProductsInCategory() {
		return null;
	}

	@Override
	public List<String> readAllCategories() {
		return null;
	}

	@Override
	public String updateCategory(String categoryName, String newStaffResponsible) {
		return null;
	}

	@Override
	public String deleteCategory(String categoryName) {
		return null;
	}
}
