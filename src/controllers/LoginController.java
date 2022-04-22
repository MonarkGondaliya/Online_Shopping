package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.LoginModel;

//Create LoginController class which create all controller class for Login view

public class LoginController {

	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Label lblError;

	private LoginModel model;
	public static String userName;

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		LoginController.userName = userName;
	}

	public LoginController() {
		model = new LoginModel();
	}

	//Create login method which checks the validation for user
	public void login() {

		lblError.setText("");
		lblError.setVisible(Boolean.FALSE);
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();

		// Validations
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setVisible(Boolean.TRUE);
			lblError.setText("Username / Password cannot be empty");
			return;
		}
		if (username == null || username.trim().equals("")) {
			lblError.setVisible(Boolean.TRUE);
			lblError.setText("Username cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setVisible(Boolean.TRUE);
			lblError.setText("Password cannot be empty or spaces");
			return;
		}
		

		// authentication check
		checkCredentials(username, password);

	}

	//Create method which authorize the user and redirect to Admin or client view screen
	public void checkCredentials(String username, String password) {
		Boolean isValid = model.getCredentials(username, password);
		if (!isValid) {
			lblError.setVisible(Boolean.TRUE);
			lblError.setText("User does not exist!");
			return;
		}
		try {
			AnchorPane root;
			Main.setUserId(username);
			if (model.isAdmin() && isValid) {
				// If user is admin, inflate admin view
				System.out.println("Logged in as Admin");
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
				Main.stage.setTitle("Admin View - " + username);

			} else {
				// If user is customer, inflate customer view
				System.out.println("Logged in as " + username);
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ClientView.fxml"));

				ClientController.setUserName(username);
				Main.stage.setTitle("Client View - " + username);
			}

			Scene scene = new Scene(root);
			Main.stage.setScene(scene);

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}
	//Create exit method which allow user to exit from application
	public void exit() {
		System.exit(0);
		System.out.println("User has closed the Application");
	}
}