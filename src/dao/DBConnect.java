package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DBConnect class create connection to connect with database
public class DBConnect {

	protected Connection connection;

	public Connection getConnection() {
		return connection;
	}

	private static String url = "jdbc:mysql://www.papademas.net:3307/510fp";
	private static String username = "fp510";
	private static String password = "510";

	//Create method to connect with database
	public DBConnect() {
		try {
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(Boolean.FALSE);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
	}
}
