package controllers;

import java.sql.Statement;

import application.Main;
import dao.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.ClientModal;
import models.Product;

//Create ClientController class which create all controller class for Client view

public class ClientController {

	@FXML
	private TableView<Product> objs;
	@FXML
	private TableView<Product> objs1;

	private ClientModal clientModal;

	public static String userName;

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		ClientController.userName = userName;
	}

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	//Create constructor 
	public ClientController() {
		conn = new DBConnect();
	}

	//Create initialize method for table contents
	@FXML
	public void initialize() {
		String vals[] = { "name", "brand", "price" };
		for (int i = 0; i < 3; i++) {
			objs.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(vals[i]));
			clientModal = new ClientModal();
		}

		String vals1[] = { "name", "brand", "price" };
		for (int i = 0; i < 3; i++) {
			objs1.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(vals1[i]));
			clientModal = new ClientModal();

		}
		update();
	}

	//Create delete record method to delete data from my cart
	public void deleteRec() {
		if (null != objs1.getSelectionModel().getSelectedItem()) {
			System.out.println("Deleting the product from my cart...");
			clientModal.deleteFromMyCart(objs1.getSelectionModel().getSelectedItem());
			update();
			System.out.println("Deleting the product from my cart...: DONE");

		}//Alert message if user not selected Product from table
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setHeaderText("Please select Product from cart");
			alert.showAndWait();
		}
	}

	//Create method to add products in to my cart
	public void addToCart() {
		if (null != objs.getSelectionModel().getSelectedItem()) {
			System.out.println("Adding to product to my cart...");
			clientModal.insertToMyCart(Main.userId, objs.getSelectionModel().getSelectedItem());
			update();
			System.out.println("Adding to product to my cart...: DONE");
		}
		//Alert message if user not selected Product from table
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setHeaderText("Please select Product from Product list");
			alert.showAndWait();
		}

	}

	//Create method to update table contents
	private void update() {
		objs.getItems().clear();
		objs.getItems().addAll(clientModal.selectProduct());

		objs1.getItems().clear();
		objs1.getItems().addAll(clientModal.selectMyCart(Main.userId));
	}

	//Create Logout method to redirect user to login page
	public void logout() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
			System.out.println("Logged out as " + userName);
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e.getMessage());
		}
	}

	//Create Exit method which allow user to exit from application
	public void exit() {
		System.exit(0);
		System.out.println("User has closed the Application");
	}

}