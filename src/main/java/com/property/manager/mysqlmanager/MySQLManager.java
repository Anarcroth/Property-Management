package com.property.manager.mysqlmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(MySQLManager.class);

	private static final String URL = "http://is2:81/MDN150/";

	private static final String USER = "MDN150";

	private static final String PASSWRD = "rP8Pm9";

	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private Connection connect = null;

	private Statement statement = null;

	private PreparedStatement preparedStatement = null;

	private ResultSet resultSet = null;

	public void readDB() {

		try {

			// Load the SQL driver
			Class.forName(DRIVER_CLASS);

			connect = DriverManager.getConnection(URL, USER, PASSWRD);

			statement = connect.createStatement();

			resultSet = statement.executeQuery("");

			writeResultSet(resultSet);

			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect.prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");

			// "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
			// Parameters start with 1

			preparedStatement.setString(1, "Test");
			preparedStatement.setString(2, "TestEmail");
			preparedStatement.setString(3, "TestWebpage");
			preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
			preparedStatement.setString(5, "TestSummary");
			preparedStatement.setString(6, "TestComment");
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");

			resultSet = preparedStatement.executeQuery();

			writeResultSet(resultSet);

			// Remove again the insert comment
			preparedStatement = connect.prepareStatement("delete from feedback.comments where myuser= ? ; ");

			preparedStatement.setString(1, "Test");

			preparedStatement.executeUpdate();

			resultSet = statement.executeQuery("select * from feedback.comments");
			writeMetaData(resultSet);

		} catch (Exception e) {

			LOGGER.error("Could not read from the DB.", e);

		} finally {

			close();
		}
	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {

		LOGGER.info("The columns in the table are: ");

		LOGGER.info("Table: " + resultSet.getMetaData().getTableName(1));

		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {

			LOGGER.info("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");

			LOGGER.info("User: " + user);
			LOGGER.info("Website: " + website);
			LOGGER.info("summary: " + summary);
			LOGGER.info("Date: " + date);
			LOGGER.info("Comment: " + comment);
		}
	}

	// You need to close the resultSet
	private void close() {

		try {
			if (resultSet != null) {

				resultSet.close();
			}

			if (statement != null) {

				statement.close();
			}

			if (connect != null) {

				connect.close();
			}
		} catch (Exception e) {

			LOGGER.error("Could not close connection with the DB.", e);
		}
	}
}
