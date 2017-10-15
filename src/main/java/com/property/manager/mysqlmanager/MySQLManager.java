package com.property.manager.mysqlmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(MySQLManager.class);

	public static MySQLManager instance = null;

	private static final String URL = "jdbc:mysql://localhost:3306/property_management";

	private static final String USER = "aubgstudent";

	private static final String PASSWRD = "aubgstudent";

	public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

	private PreparedStatement preparedStatement = null;

	private Connection connection = null;

	private Statement statement = null;

	private ResultSet resultSet = null;

	private MySQLManager() {

		try {
			// Load the driver class
			Class.forName(DRIVER_CLASS);

		} catch (ClassNotFoundException cnfe) {

			LOGGER.error("Could not load the driver class.", cnfe);
		}
	}

	public static void init() {

		if (instance == null) {

			instance = new MySQLManager();

			// Automatically connect to the DB.
			instance.getConnection();
		}
	}

	public static MySQLManager get() {

		return instance;
	}

	public void createConnection() {

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWRD);

			if (connection != null) {

				LOGGER.info("You made it, take control your database now!");

			} else {

				LOGGER.info("Failed to make connection!");
			}

		} catch (SQLException sqle) {

			LOGGER.error("Could not connect to the DB.", sqle);
		}
	}

	public Connection getConnection() {

		return connection;
	}

	private void readDB() {

		try {

			statement = connection.createStatement();

			resultSet = statement.executeQuery("");

		} catch (Exception e) {

			LOGGER.error("Could not read from the DB.", e);

		} finally {

			close();
		}
	}

	public String getUserRole() {

		return "ROLE";
	}

	public String getUserID() {

		return "ID";
	}

	public String findUser(String username) {

		try {

			statement = connection.createStatement();

			// Send a query to find the user that is paased as an argument.
			resultSet = statement.executeQuery("SELECT " + username + " FROM USERS");

		} catch (SQLException sqle) {

			LOGGER.error("Could not find user.", sqle);
		}

		return resultSet.toString();
	}

	// Close the resultSet, statement, and connection.
	private void close() {

		try {
			if (resultSet != null) {

				resultSet.close();
			}

			if (statement != null) {

				statement.close();
			}

			if (connection != null) {

				connection.close();
			}
		} catch (Exception e) {

			LOGGER.error("Could not close connection with the DB.", e);
		}
	}
}
