package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Product;

//Create UpdateController class which create all controller class for Update product view

public class UpdateController implements Initializable {
	public static Product m = null;
	@FXML
	public TextField id, name, pay, brand;
	@FXML
	private Label lblError;
	private String n, p, b;
	private Integer i;

	//Create constructor
	public UpdateController(Integer id, String name, String pay, String brand) {

		this.n = name;
		this.p = pay;
		this.b = brand;
		this.i = id;
	}
	// Create Update action method which set data from selected row
	@FXML
	void UpdateAction(ActionEvent event) {
		String i = id.getText(), n = name.getText(), p = pay.getText(), b = brand.getText();
		
		if (i != null && n != null && p != null & b != null&& n.trim().length()>0 && p.trim().length()>0 && b.trim().length()>0) {
			if (!isInt(p)) {
				lblError.setVisible(Boolean.TRUE);
				lblError.setText("Price should be numeric");
				return;
			}
			System.out.println("Updating the product...");
			m = new Product(Integer.parseInt(i), n, b, Integer.parseInt(p));
			name.getScene().getWindow().hide();
			m = null;
		}
		else {
			lblError.setVisible(Boolean.TRUE);
			lblError.setText("Please enter all data filed");
		}
	}

	// Create Initialize method to set table data variables
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		name.setText(n);
		pay.setText(p);
		brand.setText(b);
		id.setText(i.toString());
	}
	
	//Create method to check value is integer or not
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
