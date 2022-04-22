package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnect;

//Create LoginModel class which create database connection class from User table
public class LoginModel extends DBConnect {

	private Boolean admin;

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	//Create method to get credentials from login page and validate
	public Boolean getCredentials(String username, String password) {

		String query = "SELECT * FROM monark_users WHERE user_name = ? and user_password = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				setAdmin(rs.getBoolean("is_admin"));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}// end class
