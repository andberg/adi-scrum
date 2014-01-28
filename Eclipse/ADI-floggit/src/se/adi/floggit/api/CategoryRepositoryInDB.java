package se.adi.floggit.api;

import java.util.List;
import se.adi.floggit.interfaces.CategoryRepository;

public final class CategoryRepositoryInDB implements CategoryRepository
{

	@Override
	public boolean createCategory(String categoryName, String staffFirstname, String staffSurname)
	{
		return false;
	}

	@Override
	public List<String> readAllCategories()
	{
		return null;
	}

	@Override
	public boolean updateCategory(String categoryName, String staffFirstname, String staffSurname)
	{
		return false;
	}

	@Override
	public boolean deleteCategory(String categoryName)
	{
		return false;
	}
}
