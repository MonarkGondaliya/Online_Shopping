package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Product;

// Create Controller for AddController
public class AddController {
	public static Product product = null;
	@FXML
	private Label lblError;
	@FXML
	public TextField name, price, brand;

	//AddAction event function 
	@FXML
	void AddAction(ActionEvent event) {
		String n = name.getText(), p = price.getText(), b = brand.getText();
		if (n != null && p != null & b != null && n.trim().length()>0 && p.trim().length()>0 && b.trim().length()>0) {
			if (!isInt(p)) {
				lblError.setVisible(Boolean.TRUE);
				lblError.setText("Price should be numeric");
				return;
			}
			product = new Product(null, n, b, Integer.parseInt(p));
			name.getScene().getWindow().hide();
			product = null;
		}
		//Error message if all inputs are blank
		else {
			lblError.setVisible(Boolean.TRUE);
			lblError.setText("Please enter all data filed");
		}
	}
	private boolean isInt(String str) 
	{ 
	    try 
	    { 
	        Integer.parseInt(str); 
	        return true; 
	    } 
	    catch (NumberFormatException e) 
	    { 
	         
	        return false; 
	    } 
	}
}
