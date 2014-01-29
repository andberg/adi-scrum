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
import se.adi.floggit.interfaces.ProductRepository;

public final class ProductRepositoryInDB implements ProductRepository
{

	@Override
	public boolean createProduct(Product product)
	{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		String query = null;
		boolean created = false;
		List<String> categories = product.getCategories();
		List<String> categoriesIds = new ArrayList<String>();
		int generatedId = 0;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);
			
			query = "SELECT id FROM categories WHERE categories.name IN (";
			
			for (int i = 0; i < categories.size(); i++) {
				if (i < categories.size() - 1) {
					query += "?, ";
				} else {
					query += "?";
				}
			}
			query += ")";
			
			pstmt = connection.prepareStatement(query);
			
			for (int i = 0; i < categories.size(); i++) {
				pstmt.setString(i + 1, categories.get(i));
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				 categoriesIds.add(rs.getString("id"));
			}
			
			if (product.getCategories().size() == categoriesIds.size()){
				System.out.println("The number of categories in DB is the same as the categories listed in product to be created");
			} else {
				System.out.println("One or more specified categories were not found in the database.");
				System.out.println(product.getCategories().size() + " " + categoriesIds.size());
				return false;
			}
			
			rs.close();
			pstmt.close();
			
			query = "INSERT INTO products "
					+ "(name,description,cost,rrp) "
					+ "VALUES (?, ?, ?, ?)";
			pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getDescription());
			pstmt.setDouble(3, product.getCost());
			pstmt.setDouble(4, product.getRrp());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
			
			for (int i = 0; i < categoriesIds.size(); i++) {
				query = "INSERT INTO products_in_categories (product_id, category_id) VALUES (" + generatedId + ", " + categoriesIds.get(i) + ")";
				stmt = connection.createStatement();
				stmt.executeUpdate(query);
			}
			
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
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try
			{
				pstmt.close();
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
		Statement stmtC = null;
		Connection connection = null;
		String query = null;
		List<Product> productList = new ArrayList<Product>();

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "SELECT * FROM products";
			stmt = connection.createStatement();
			rsP = stmt.executeQuery(query);

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
				stmtC = connection.createStatement();

				query = "SELECT categories.name" +
						" FROM products INNER JOIN products_in_categories" +
						" ON products.id = products_in_categories.product_id" +
						" INNER JOIN categories ON products_in_categories.category_id = categories.id"
						+ " WHERE products.id = " + id + ";";

				rsC = stmtC.executeQuery(query);
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
				stmtC.close();
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
	public boolean updateProduct(int id, Product product)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "UPDATE products SET name = ?, description = ?, cost = ?, "
					+ "rrp = ? WHERE id = '" + id + "'";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getDescription());
			pstmt.setDouble(3, product.getCost());
			pstmt.setDouble(4, product.getRrp());

			pstmt.execute();
			return true;
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
