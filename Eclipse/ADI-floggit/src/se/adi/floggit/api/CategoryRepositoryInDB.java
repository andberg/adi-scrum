package se.adi.floggit.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import se.adi.floggit.interfaces.CategoryRepository;

public final class CategoryRepositoryInDB implements CategoryRepository
{

	@Override
	public boolean createCategory(String categoryName, String staffFirstname, String staffSurname)
	{
		return updateAndCreate(categoryName, staffFirstname, staffSurname, "INSERT INTO categories (staff_responsible, name) VALUES (?, ?)");
	}

	@Override
	public boolean updateCategory(String categoryName, String staffFirstname, String staffSurname)
	{
		return updateAndCreate(categoryName, staffFirstname, staffSurname, ("UPDATE categories SET staff_responsible = ? WHERE name = ?"));
	}

	@Override
	public List<String> readAllCategories()
	{
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		String query = null;
		List<String> categoriesList = new ArrayList<String>();

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			stmt = connection.createStatement();

			query = "SELECT name FROM categories";

			rs = stmt.executeQuery(query);

			while (rs.next())
			{
				categoriesList.add(rs.getString("name"));
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return categoriesList;

	}

	@Override
	public boolean deleteCategory(String categoryName)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		int rowsAffected = 0;
		boolean deleted = false;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "DELETE FROM categories WHERE name = ?";

			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, categoryName);

			rowsAffected = pstmt.executeUpdate();
			deleted = (rowsAffected > 0);

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return deleted;

	}

	private boolean updateAndCreate(String categoryName, String staffFirstname, String staffSurname, String queryIn)
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		boolean returnValue = false;
		int staffId = 0;
		int affectedRows = 0;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "SELECT id FROM staff WHERE firstname = ? AND surname = ?";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, staffFirstname);
			pstmt.setString(2, staffSurname);

			rs = pstmt.executeQuery();

			while (rs.next())
			{
				staffId = rs.getInt("id");
			}

			pstmt.close();

			pstmt = connection.prepareStatement(queryIn);

			pstmt.setInt(1, staffId);
			pstmt.setString(2, categoryName);

			affectedRows = pstmt.executeUpdate();
			returnValue = (affectedRows > 0);
			return returnValue;
		}

		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (connection != null)
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return returnValue;

	}
}
