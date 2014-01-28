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

public final class ProductRepositoryInDB implements ProductRepository {

	@Override
	public boolean createProduct(Product product) {
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
	public List<Product> readProductsInCategory(String categoryName) {
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		String query = null;
		List<Product> productList = new ArrayList<Product>();
		return productList;
	}
	
	@Override
	public List<Product> readAllProducts() {
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		String query = null;
		List<Product> productList = new ArrayList<Product>();

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			stmt = connection.createStatement();

			query = "SELECT * FROM products";

			rs = stmt.executeQuery(query);
			
			int id; 
			String name; 
			String description; 
			double cost; 
			double rrp; 
			List<String> categoryList = new ArrayList<String>();

			while (rs.next())
			{
				id = rs.getInt("id");  
				name = rs.getString("name"); 
				description = rs.getString("description"); 
				cost = rs.getDouble("cost"); 
				rrp = rs.getDouble("rrp"); 
				
				query = "SELECT products.name, categories.name "
						+ "FROM products INNER JOIN products_in_categories "
						+ "ON products.id = products_in_categories.product_id"
						+ "INNER JOIN categories ON products_in_categories.category_id = categories.id"
						+ "WHERE products.id = " + id + ";"; 
				
				rs = stmt.executeQuery(query); 
						while (rs.next()){
							categoryList.add(rs.getString("name"));
						}
						
				Product product = new Product(id, name, description, cost, rrp, categoryList); 
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
				rs.close();
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
	public boolean updateProduct(String productName) {
		return false;
	}

	@Override
	public boolean deleteProduct(int id) {
		return false;
	}
}
