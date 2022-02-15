package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private String url = "jdbc:mysql://localhost:3306/backend_test?useSSL=false";
	private String driver = "com.mysql.jdbc.Driver";
	private String jdbcUser = "test";
	private String jdbcPassword = "test";

	private String INSERT_USERS = "INSERT INTO user_list(name, email, country) VALUES(?,?,?)";
	private String SELECT_USER_BY_ID = "SELECT id,name,email,country FROM user_list WHERE id=?";
	private String SELECT_ALL_USERS = "SELECT id,name,email,country FROM user_list";
	private String DELETE_USER = "DELETE FROM user_list WHERE id=?;";
	private String UPDATE_USER = "UPDATE user_list SET name=?, email=?, country=? WHERE id=?;";

	protected Connection openConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(driver);

		// getConnection
		connection = DriverManager.getConnection(url, jdbcUser, jdbcPassword);
		return connection;
	}

	public void insertUserRecord(User user) throws ClassNotFoundException, SQLException {
		System.out.println("Entering insertUserRecord");
		Connection connection = openConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getCountry());
		preparedStatement.executeUpdate();

		if (connection != null)
			connection.close();
	}

	public boolean updateUserRecord(User user) throws ClassNotFoundException, SQLException {
		System.out.println("Entering UpdateUserRecord");
		Connection connection = openConnection();
		boolean rowUpdated;
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getCountry());
		preparedStatement.setInt(4, user.getId());
		rowUpdated = preparedStatement.executeUpdate() > 0;

		if (connection != null)
			connection.close();
		return rowUpdated;
	}

	public User selectUser(int id) throws Exception {
		System.out.println("Entering selectUser");
		Connection connection = openConnection();
		User user = null;
		// execute Query
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
		preparedStatement.setInt(1, id);
		System.out.println("preparedStatement::"+preparedStatement);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String country = resultSet.getString("country");
			user = new User(id,name, email, country);
		}
		if (connection != null)
			connection.close();
		return user;
	}

	public List<User> fetchAllUsers() throws ClassNotFoundException, SQLException {
		System.out.println("Entering fetchAllUsers");
		Connection connection = openConnection();
		User user = null;
		// execute Query
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
		ResultSet resultSet = statement.executeQuery();
		List<User> list = new ArrayList<User>();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String country = resultSet.getString("country");
			list.add(new User(id, name, email, country));
		}
		if (connection != null)
			connection.close();
		return list;
	}

	public boolean deleteUserRecord(int id) throws ClassNotFoundException, SQLException {
		System.out.println("Entering deleteUserRecord");
		Connection connection = openConnection();
		boolean rowDeleted;
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
		preparedStatement.setInt(1, id);
		rowDeleted = preparedStatement.executeUpdate() > 0;

		if (connection != null)
			connection.close();
		return rowDeleted;
	}

}