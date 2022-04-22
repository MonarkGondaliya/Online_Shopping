package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//Main class which load the login view
public class Main extends Application {

	public static Stage stage; // set global stage object!!!
	public static String userId;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Login View");
			stage.setScene(scene);
			stage.show();
			System.out.println("Login page loaded...");

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		Main.userId = userId;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
