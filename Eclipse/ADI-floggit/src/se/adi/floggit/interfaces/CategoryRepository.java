package se.adi.floggit.interfaces;

import java.util.List;

import se.adi.floggit.api.Response;
import se.adi.floggit.api.ResponseType;

public interface CategoryRepository {
	ResponseType createCategory(String categoryName, String staffFirstname,
			String staffSurname);

	Response<List<String>> readAllCategories();

	ResponseType updateCategory(String categoryName, String staffFirstname,
			String staffSurname);

	ResponseType deleteCategory(String categoryName);
}
