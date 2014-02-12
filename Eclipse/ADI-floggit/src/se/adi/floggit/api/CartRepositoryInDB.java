package se.adi.floggit.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import se.adi.floggit.classes.Product;
import se.adi.floggit.interfaces.CartRepository;

public final class CartRepositoryInDB implements CartRepository
{

	@Override
	public Response<Map<Product, Integer>> readCart(String email)
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		Product product = null;
		Map<Product, Integer> cart = new LinkedHashMap<Product, Integer>();

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);
			
			if (!userInDatabase(email, connection)) {
				return new Response<Map<Product, Integer>>(ResponseType.USER_EMAIL_NOT_FOUND, cart);
			}

			query = "SELECT products.id, products.name, products.rrp, quantity "
					+ "FROM users INNER JOIN carts ON users.id = carts.user_id "
					+ "INNER JOIN products ON carts.product_id = products.id "
					+ "WHERE email = ?";

			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				return new Response<Map<Product, Integer>>(ResponseType.USER_CART_EMPTY, cart);
			}

			while (rs.next())
			{
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("rrp"));
				cart.put(product, rs.getInt("quantity"));
			}
			return new Response<Map<Product, Integer>>(ResponseType.SERVER_CONNECTION_SUCCESSFUL, cart);
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
				if (rs != null) {
					rs.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return new Response<Map<Product, Integer>>(ResponseType.SERVER_CONNECTION_FAILED, cart);
	}

	@Override
	public ResponseType updateCart(String email, int productId, int quantity)
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		int userId = 0;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "SELECT id FROM users WHERE email = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				userId = rs.getInt("id");
			}
			else
			{
				return ResponseType.USER_EMAIL_NOT_FOUND;
			}

			rs.close();
			pstmt.close();

			query = "SELECT id FROM products WHERE products.id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();

			if (!rs.isBeforeFirst())
			{
				return ResponseType.PRODUCT_NOT_FOUND;
			}

			rs.close();
			pstmt.close();

			query = "SELECT * FROM carts "
					+ "INNER JOIN users ON carts.user_id = users.id "
					+ "INNER JOIN products ON products.id = carts.product_id "
					+ "WHERE carts.user_id = ? AND carts.product_id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, productId);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				query = "UPDATE carts SET quantity = ? "
						+ "WHERE user_id = ? AND product_id = ?";
			}
			else
			{
				query = "INSERT INTO carts (quantity, user_id, product_id) "
						+ "VALUES (?, ?, ?)";
			}

			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, productId);
			pstmt.executeUpdate();

			return ResponseType.USER_CART_UPDATED;

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
				if (rs != null) {
					rs.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (connection != null) {
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

	@Override
	public ResponseType deleteFromCart(String email, int productId)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		int affectedRows = 0;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);
			
			if (!userInDatabase(email, connection)) {
				return ResponseType.USER_EMAIL_NOT_FOUND;
			}

			query = "DELETE FROM carts WHERE product_id = ? "
					+ "AND user_id = (SELECT id FROM users WHERE email = ?)";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, productId);
			pstmt.setString(2, email);
			affectedRows = pstmt.executeUpdate();
			
			if (affectedRows > 0) {
				return ResponseType.USER_CART_UPDATED;
			}
			return ResponseType.USER_CART_NOT_UPDATED;
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
				if (pstmt != null) {
					pstmt.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if (connection != null) {
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
	
	private boolean userInDatabase(String email, Connection connection) throws SQLException
	{
		String query = "SELECT id FROM users WHERE email = ?";

		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();

		if (rs.isBeforeFirst())
		{
			rs.close();
			pstmt.close();
			return true;
		}
		rs.close();
		pstmt.close();
		return false;
	}
}
