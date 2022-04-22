package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnect;

//Create ClientModel class which create all database connection class for Client view

public class ClientModal extends DBConnect {
	public ClientModal() {
		super();
	}

	//Create ArrayList for Product list 
	public List<Product> selectProduct() {
		List<Product> result = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM monark_product");
			while (res.next()) {
				Product m = new Product(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4));
				result.add(m);
			}
			stmt.close();
			res.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage() + ", in select in db: monark_product");
			ex.printStackTrace();
		}
		return result;
	}

	//Create ArrayList for Product list of client cart
	public List<Product> selectMyCart(String user) {
		List<Product> result = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(
					"select mmc.id, mp.*  from monark_my_cart mmc join monark_product mp on mp.id=mmc.product_id where mmc.user_name = '"
							+ user + "'");
			while (res.next()) {
				Product m = new Product(res.getInt(1), res.getString(3), res.getString(4), res.getInt(5));
				result.add(m);
			}
			stmt.close();
			res.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage() + ", in select in db: monark_product");
			ex.printStackTrace();
		}
		return result;
	}

	//Create class which create query to insert data to my cart for client view
	public void insertToMyCart(String userName, Product m) {

		try {
			if (m != null) {
				connection.createStatement().execute("INSERT INTO `monark_my_cart` (`user_name`, `product_id`) VALUES"
						+ " ('" + userName + "','" + m.getId() + "')");
				connection.commit();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage() + ", in insert in db: monark_product");
		}
	}
	
	//Create class which create query to delete from mycart for client view
	public void deleteFromMyCart(Product m) {
		try {
			connection.createStatement().execute("DELETE FROM `monark_my_cart` WHERE " + "id='" + m.getId() + "'");
			connection.commit();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage() + ", in delete in db: monark_my_cart");
		}
	}

}