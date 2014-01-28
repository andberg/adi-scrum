package se.adi.floggit.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import se.adi.floggit.classes.Product;
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
					+ "VALUES (?, ?, ?, ?, ?)";

			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getDescription());
			pstmt.setDouble(4, product.getCost());
			pstmt.setDouble(5, product.getRrp());

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
		return null;
	}
	
	@Override
	public List<Product> readAllProducts() {
		return null;
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
