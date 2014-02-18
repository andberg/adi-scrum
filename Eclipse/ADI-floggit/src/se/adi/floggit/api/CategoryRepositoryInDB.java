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
	public ResponseType createCategory(String categoryName,
			String staffFirstname, String staffSurname)
	{

		ResponseType response = categoryInDatabase(categoryName);

		if (response == ResponseType.CATEGORY_ALREADY_IN_DB
				|| response == ResponseType.SERVER_CONNECTION_FAILED)
		{
			return response;
		}

		response = updateAndCreate(categoryName, staffFirstname, staffSurname,
				"INSERT INTO categories (staff_responsible, name) VALUES (?, ?)");

		if (response == ResponseType.SERVER_CONNECTION_SUCCESSFUL)
		{
			return ResponseType.CATEGORY_CREATED;
		}
		return response;
	}

	@Override
	public ResponseType updateCategory(String categoryName,
			String staffFirstname, String staffSurname)
	{

		ResponseType response = updateAndCreate(categoryName, staffFirstname,
				staffSurname,
				"UPDATE categories SET staff_responsible = ? WHERE name = ?");

		if (response == ResponseType.SERVER_CONNECTION_SUCCESSFUL)
		{
			return ResponseType.CATEGORY_UPDATED;
		}
		return response;
	}

	@Override
	public Response<List<String>> readAllCategories()
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
			return new Response<List<String>>(
					ResponseType.SERVER_CONNECTION_SUCCESSFUL, categoriesList);
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
		return new Response<List<String>>(
				ResponseType.SERVER_CONNECTION_FAILED, categoriesList);
	}

	@Override
	public ResponseType deleteCategory(String categoryName)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		int rowsAffected = 0;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "DELETE FROM categories WHERE name = ?";

			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, categoryName);

			rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0)
			{
				return ResponseType.CATEGORY_DELETED;
			}
			return ResponseType.CATEGORY_NOT_FOUND;
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
		return ResponseType.SERVER_CONNECTION_FAILED;
	}

	private ResponseType updateAndCreate(String categoryName,
			String staffFirstname, String staffSurname, String queryIn)
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		int staffId = 0;

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
			if (!rs.isBeforeFirst())
			{
				return ResponseType.STAFF_NOT_FOUND;
			}

			if (rs.next())
			{
				staffId = rs.getInt("id");
			}

			pstmt.close();

			pstmt = connection.prepareStatement(queryIn);

			pstmt.setInt(1, staffId);
			pstmt.setString(2, categoryName);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0)
			{
				return ResponseType.SERVER_CONNECTION_SUCCESSFUL;
			}
			else
			{
				return ResponseType.CATEGORY_NOT_FOUND;
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
		return ResponseType.SERVER_CONNECTION_FAILED;
	}

	private ResponseType categoryInDatabase(String categoryName)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		ResultSet rs = null;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);

			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			String query = "SELECT * FROM categories WHERE name = ?";

			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, categoryName);
			rs = pstmt.executeQuery();

			if (rs.isBeforeFirst())
			{
				rs.close();
				pstmt.close();
				connection.close();
				return ResponseType.CATEGORY_ALREADY_IN_DB;
			}
			return ResponseType.CATEGORY_NOT_FOUND;
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
		return ResponseType.SERVER_CONNECTION_FAILED;
	}
}