package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.classes.Product;

public interface CategoryRepository {
	String createCategory(String categoryName, String staffFirstname, String staffSurname);
	List<String> readAllCategories();
	String updateCategory(String categoryName, String newStaffResponsible);
	String deleteCategory(String categoryName);
}
