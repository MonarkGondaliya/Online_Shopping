package controllers;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.AdminModel;
import models.Product;

//Create AdminController class which create all controller class for Admin view
public class AdminController {

	@FXML
	private TableView<Product> objs;	
	@FXML
	private AddController addController;	
	@FXML
	private TextField name, pay, brand;

	private Stage addstg;
	private AdminModel adminModel;

	//Logout method and redirect to login page
	public void logout() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
			System.out.println("Logged out as Admin");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e.getMessage());
		}
	}

	// Exit method exit user from application
	public void exit() {
		System.exit(0);
		System.out.println("User has closed the Application");
	}

	//initialize table contents
	@FXML
	public void initialize() {
		String vals[] = { "name", "brand", "price" };
		for (int i = 0; i < 3; i++) {
			objs.getColumns().get(i).setCellValueFactory(new PropertyValueFactory<>(vals[i]));
		}
		adminModel = new AdminModel();
		update();
	}

	//Create Delete Action event which User can delete data
	@FXML
	void DeleteAction(ActionEvent event) {
		if (null != objs.getSelectionModel().getSelectedItem()) {
			System.out.println("Deleting the product...");
			adminModel.deleteProduct(objs.getSelectionModel().getSelectedItem());
			System.out.println("Deleting the product...: DONE");
			update();
		}
		//Alert message if user not selected Product from table
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setHeaderText("Please select Product");
			alert.showAndWait();
		}
	}

	//Create Update Action event which get selected data and user can update
	@FXML
	void UpdateAction(ActionEvent event) throws IOException {

		Product material = objs.getSelectionModel().getSelectedItem();
		if (null != material) {
			UpdateController dialogController = new UpdateController(material.getId(), material.getName(),
					material.getPrice().toString(), material.getBrand());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Update.fxml"));
			loader.setController(dialogController);

			Parent root = loader.load();
			Scene scene = new Scene(root);
			addstg = new Stage();
			addstg.setTitle("Update Product");
			addstg.setScene(scene);
			addstg.setOnHiding(e -> {
				if (UpdateController.m != null) {
					System.out.println("Updating the product...");
					adminModel.update(UpdateController.m);
					update();
					UpdateController.m = null;
					addstg.close();
					System.out.println("Updating the product...: DONE");
				}
			});
			addstg.show();
		}
		//Alert message if user not selected Product from table
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setHeaderText("Please select Product");
			alert.showAndWait();
		}
	}

	//Create Add Action which open popup to add new product data
	@FXML
	void AddAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/views/Add.fxml"));
		Scene scene = new Scene(root);
		addstg = new Stage();
		addstg.setTitle("Add Product");
		addstg.setScene(scene);
		addstg.setOnHiding(e -> {
			if (AddController.product != null) {
				System.out.println("Adding the product...");
				adminModel.insertProduct(AddController.product);
				update();
				AddController.product = null;
				addstg.close();
				System.out.println("Adding the product...: DONE");
			}
		});
		addstg.show();

	}

	//Create update method which clear and load items to table
	private void update() {
		objs.getItems().clear();
		objs.getItems().addAll(adminModel.selectProduct());
	}

}
