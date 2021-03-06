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

public final class ProductRepositoryInDB implements ProductRepository {

	@Override
	public ResponseType createProduct(Product product) {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		List<Integer> categoryIds = null;
		int generatedId = 0;

		try {
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);
			connection.setAutoCommit(false);

			categoryIds = getProductCategoryIds(product.getCategories(),
					connection);

			if (product.getCategories().size() != categoryIds.size()) {
				return ResponseType.CATEGORY_NOT_FOUND;
			}

			String query = "INSERT INTO products "
					+ "(name,description,cost,RRP) " + "VALUES (?, ?, ?, ?)";
			pstmt = connection.prepareStatement(query,
					PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getDescription());
			pstmt.setDouble(3, product.getCost());
			pstmt.setDouble(4, product.getRRP());
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}

			for (int i = 0; i < categoryIds.size(); i++) {
				query = "INSERT INTO products_in_categories (product_id, category_id) VALUES ("
						+ generatedId + ", " + categoryIds.get(i) + ")";
				stmt = connection.createStatement();
				stmt.executeUpdate(query);
			}
			connection.commit();

			return ResponseType.PRODUCT_CREATED;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ResponseType.SERVER_CONNECTION_FAILED;
	}

	@Override
	public Response<List<String>> readProductsInCategory(String categoryName) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		List<String> productList = new ArrayList<String>();

		try {
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

			while (rs.next()) {
				productList.add(rs.getString("name"));
			}

			return new Response<List<String>>(
					ResponseType.SERVER_CONNECTION_SUCCESSFUL, productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Response<List<String>>(
				ResponseType.SERVER_CONNECTION_FAILED, productList);
	}

	@Override
	public Response<List<Product>> readProduct(String productName) {
		ResultSet rs = null;
		ResultSet rsC = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		Connection connection = null;
		String query = null;
		List<Product> productList = new ArrayList<Product>();

		try {
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "SELECT * FROM products" + " WHERE name = ?";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double cost = rs.getDouble("cost");
				double RRP = rs.getDouble("RRP");
				List<String> categories = new ArrayList<String>();
				stmt = connection.createStatement();

				query = "SELECT categories.name"
						+ " FROM products INNER JOIN products_in_categories"
						+ " ON products.id = products_in_categories.product_id"
						+ " INNER JOIN categories ON products_in_categories.category_id = categories.id"
						+ " WHERE products.id = " + id;

				rsC = stmt.executeQuery(query);

				while (rsC.next()) {
					categories.add(rsC.getString("name"));
				}
				Product product = new Product(id, name, description, cost, RRP,
						categories);
				productList.add(product);
			}
			return new Response<List<Product>>(
					ResponseType.SERVER_CONNECTION_SUCCESSFUL, productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rsC != null) {
					rsC.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Response<List<Product>>(
				ResponseType.SERVER_CONNECTION_FAILED, productList);
	}

	@Override
	public Response<List<Product>> readAllProducts() {
		ResultSet rsP = null;
		ResultSet rsC = null;
		Statement stmt = null;
		Statement stmtC = null;
		Connection connection = null;
		String query = null;
		List<Product> productList = new ArrayList<Product>();

		try {
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);

			query = "SELECT * FROM products";
			stmt = connection.createStatement();
			rsP = stmt.executeQuery(query);

			while (rsP.next()) {
				int id = rsP.getInt("id");
				String name = rsP.getString("name");
				String description = rsP.getString("description");
				double cost = rsP.getDouble("cost");
				double RRP = rsP.getDouble("RRP");
				List<String> categories = new ArrayList<String>();
				stmtC = connection.createStatement();

				query = "SELECT categories.name"
						+ " FROM products INNER JOIN products_in_categories"
						+ " ON products.id = products_in_categories.product_id"
						+ " INNER JOIN categories ON products_in_categories.category_id = categories.id"
						+ " WHERE products.id = " + id + ";";

				rsC = stmtC.executeQuery(query);
				while (rsC.next()) {
					categories.add(rsC.getString("name"));
				}

				Product product = new Product(id, name, description, cost, RRP,
						categories);
				productList.add(product);
			}
			return new Response<List<Product>>(
					ResponseType.SERVER_CONNECTION_SUCCESSFUL, productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rsP != null) {
					rsP.close();
				}
				if (rsC != null) {
					rsC.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (stmtC != null) {
					stmtC.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new Response<List<Product>>(
				ResponseType.SERVER_CONNECTION_FAILED, productList);
	}

	@Override
	public ResponseType updateProduct(int id, Product product) {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		Connection connection = null;
		String query = null;
		List<Integer> categoryIds = null;
		int categoriesDeleted = 0;

		try {
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);
			connection.setAutoCommit(false);

			query = "DELETE FROM products_in_categories WHERE product_id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			categoriesDeleted = pstmt.executeUpdate();
			if (categoriesDeleted == 0) {
				return ResponseType.PRODUCT_NOT_FOUND;
			}

			pstmt.close();

			query = "UPDATE products SET name = ?, description = ?, cost = ?, "
					+ "RRP = ? WHERE id = ?";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getDescription());
			pstmt.setDouble(3, product.getCost());
			pstmt.setDouble(4, product.getRRP());
			pstmt.setInt(5, id);

			pstmt.executeUpdate();

			categoryIds = getProductCategoryIds(product.getCategories(),
					connection);

			if (categoryIds.size() != product.getCategories().size()) {
				return ResponseType.CATEGORY_NOT_FOUND;
			}

			for (int i = 0; i < categoryIds.size(); i++) {
				query = "INSERT INTO products_in_categories (product_id, category_id) VALUES ("
						+ id + ", " + categoryIds.get(i) + ")";
				stmt = connection.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
			}

			connection.commit();
			return ResponseType.PRODUCT_UPDATED;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ResponseType.SERVER_CONNECTION_FAILED;
	}

	@Override
	public ResponseType deleteProduct(int id) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		String query = null;
		boolean deleted = false;
		int effectedRows = 0;

		try {
			Class.forName(DBInfo.DRIVER_CLASS);
			connection = DriverManager.getConnection(DBInfo.URL, DBInfo.USER,
					DBInfo.PASSWORD);
			query = "DELETE FROM products WHERE id = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);

			effectedRows = pstmt.executeUpdate();
			deleted = effectedRows > 0;

			if (deleted) {
				return ResponseType.PRODUCT_DELETED;
			}
			return ResponseType.PRODUCT_NOT_FOUND;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ResponseType.SERVER_CONNECTION_FAILED;
	}

	private List<Integer> getProductCategoryIds(List<String> categories,
			Connection connection) throws SQLException {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Integer> categoryIds = new ArrayList<Integer>();
		String query = "SELECT id FROM categories WHERE categories.name IN (";

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

		while (rs.next()) {
			categoryIds.add(rs.getInt("id"));
		}
		return categoryIds;
	}
}