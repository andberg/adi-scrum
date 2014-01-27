package se.adi.floggit.interfaces;

import java.util.List;

public interface CategoryRepository {
	String createCategory(String categoryName, String staffFirstname, String staffSurname);
	List<String> readAllCategories();
	String updateCategory(String categoryName, String staffFirstname, String staffSurname);
	String deleteCategory(String categoryName);
}
