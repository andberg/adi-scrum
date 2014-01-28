package se.adi.floggit.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import se.adi.floggit.classes.Product;
import se.adi.floggit.classes.User;
import se.adi.floggit.interfaces.ProductRepository;

public final class ProductRepositoryInDB implements ProductRepository
{

	@Override
	public boolean createProduct(Product product)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		boolean created = false;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "INSERT INTO products "
					+ "(name,description,cost,rrp) "
					+ "VALUES (?, ?, ?, ?)";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getDescription());
			pstmt.setDouble(3, product.getCost());
			pstmt.setDouble(4, product.getRrp());

			pstmt.execute();
			created = true;

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
				pstmt.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return created;
	}

	@Override
	public List<String> readProductsInCategory(String categoryName)
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		List<String> productList = new ArrayList<String>();

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "SELECT products.name FROM products INNER JOIN products_in_categories "
					+ " ON products.id = products_in_categories.product_id"
					+ " INNER JOIN categories ON products_in_categories.category_id = categories.id"
					+ " WHERE categories.name = ?";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, categoryName);
			rs = pstmt.executeQuery();

			while (rs.next())
			{
				productList.add(rs.getString("name"));
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
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				pstmt.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return productList;
	}

	public List<Product> readAllProducts()
	{
		ResultSet rsP = null;
		ResultSet rsC = null;
		Statement stmt = null;
		Connection connection = null;
		String queryP = null;
		String queryC = null;
		List<Product> productList = new ArrayList<Product>();

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			queryP = "SELECT * FROM products";
			stmt = connection.createStatement();
			rsP = stmt.executeQuery(queryP);

			int id;
			String name;
			String description;
			double cost;
			double rrp;
			List<String> categories;

			while (rsP.next())
			{
				id = rsP.getInt("id");
				name = rsP.getString("name");
				description = rsP.getString("description");
				cost = rsP.getDouble("cost");
				rrp = rsP.getDouble("rrp");
				categories = new ArrayList<String>();

				queryC = "SELECT categories.name" +
						" FROM products INNER JOIN products_in_categories" +
						" ON products.id = products_in_categories.product_id" +
						" INNER JOIN categories ON products_in_categories.category_id = categories.id"
						+ " WHERE products.id = " + id + ";";

				rsC = stmt.executeQuery(queryC);
				while (rsC.next())
				{
					categories.add(rsC.getString("name"));
				}

				Product product = new Product(id, name, description, cost, rrp, categories);
				productList.add(product);

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
				rsP.close();
				rsC.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				stmt.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return productList;
	}

	@Override
	public boolean updateProduct(String productName)
	{
		return false;
	}

	@Override
	public boolean deleteProduct(int id)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		boolean deleted = false;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);
			query = "DELETE FROM products WHERE id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);

			pstmt.execute();
			deleted = true;
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
				pstmt.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return deleted;
	}
}
