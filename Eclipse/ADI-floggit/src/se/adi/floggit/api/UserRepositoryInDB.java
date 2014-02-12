package se.adi.floggit.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import se.adi.floggit.classes.User;
import se.adi.floggit.interfaces.UserRepository;

public final class UserRepositoryInDB implements UserRepository
{

	@Override
	public ResponseType createUser(User user)
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);
			
			query = "SELECT id FROM users WHERE email = ?";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, user.getEmail());
			rs = pstmt.executeQuery();
			
			if (rs.isBeforeFirst()) {
				return ResponseType.USER_NOT_CREATED;
			}
			
			pstmt.close();

			query = "INSERT INTO users "
					+ "(email,password,firstname,surname,street_address,postcode,town,phonenumber) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstname());
			pstmt.setString(4, user.getSurname());
			pstmt.setString(5, user.getStreetAddress());
			pstmt.setString(6, user.getPostcode());
			pstmt.setString(7, user.getTown());
			pstmt.setString(8, user.getPhonenumber());

			pstmt.executeUpdate();
			return ResponseType.USER_CREATED;
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

	@Override
	public Response readAllUsers()
	{
		ResultSet rs = null;
		Statement stmt = null;
		Connection connection = null;
		String query = null;
		List<User> usersList = new ArrayList<User>();

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			stmt = connection.createStatement();

			query = "SELECT * FROM users";

			rs = stmt.executeQuery(query);

			while (rs.next())
			{
				User user = new User(rs.getString("email"),
						rs.getString("password"), rs.getString("firstname"),
						rs.getString("surname"),
						rs.getString("street_address"),
						rs.getString("postcode"), rs.getString("town"),
						rs.getString("phonenumber"));
				usersList.add(user);
			}
			return new Response(ResponseType.SERVER_CONNECTION_SUCCESSFUL, usersList);
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
		return new Response(ResponseType.SERVER_CONNECTION_FAILED, usersList);
	}

	@Override
	public ResponseType updateUser(String email, User user)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		int rowsAffected = 0;
		boolean updated = false;

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "UPDATE users SET email = ?, password = ?, firstname = ?, "
					+ "surname = ?, street_address = ?, postcode = ?, town = ?, phonenumber = ?"
					+ " WHERE email = '" + email + "'";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstname());
			pstmt.setString(4, user.getSurname());
			pstmt.setString(5, user.getStreetAddress());
			pstmt.setString(6, user.getPostcode());
			pstmt.setString(7, user.getTown());
			pstmt.setString(8, user.getPhonenumber());

			rowsAffected = pstmt.executeUpdate();
			updated = (rowsAffected > 0);
			
			if (updated) {
				return ResponseType.USER_UPDATED;
 			}
			return ResponseType.USER_NOT_UPDATED;
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

	@Override
	public ResponseType deleteUser(String email)
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

			query = "DELETE FROM users WHERE email = ?";

			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, email);

			rowsAffected = pstmt.executeUpdate();
			deleted = (rowsAffected > 0);
			
			if (deleted) {
				return ResponseType.USER_DELETED;
			}
			return ResponseType.USER_NOT_DELETED;

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
	
	@Override
	public ResponseType login(String email, String password)
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		boolean login = false;
		

		try
		{
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "SELET password FROM users WHERE email = ?";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next())
			{
				login = rs.getString("password").equals(password);
			}
			
			if (login) {
				return ResponseType.LOGIN_SUCCESSFUL;
			}
			return ResponseType.LOGIN_FAILED;
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
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
}
