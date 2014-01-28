package se.adi.floggit.interfaces;

import java.util.List;

public interface CategoryRepository
{
	boolean createCategory(String categoryName, String staffFirstname, String staffSurname);

	List<String> readAllCategories();

	boolean updateCategory(String categoryName, String staffFirstname, String staffSurname);

	boolean deleteCategory(String categoryName);
}
