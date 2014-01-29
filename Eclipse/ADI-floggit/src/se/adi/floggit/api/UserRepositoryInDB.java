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
	public boolean createUser(User user)
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
	public List<User> readAllUsers()
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
		return usersList;
	}

	@Override
	public boolean updateUser(String email, User user)
	{
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;

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
	public boolean deleteUser(String email)
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

			query = "DELETE FROM users WHERE email = ?";

			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, email);

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

	@Override
	public boolean login(String email, String password)
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

			query = "SELECT password FROM users WHERE email = ?";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next())
			{
				login = rs.getString("password").equals(password);
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
		return login;
	}
}
