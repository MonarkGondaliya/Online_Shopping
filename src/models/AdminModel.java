package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnect;

//Create AdminModel class which create all database connection class for Admin view
public class AdminModel extends DBConnect {
	public AdminModel() {
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

	//Create class which create insert query for admin view
	public void insertProduct(Product m) {

		try {
			if (m != null) {
				connection.createStatement().execute("INSERT INTO `monark_product`(`name`, `price`,`brand`) VALUES"
						+ " ('" + m.getName() + "','" + m.getPrice() + "','" + m.getBrand() + "')");
				connection.commit();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage() + ", in insert in db: monark_product");
		}
	}

	//Create class which create delete query for admin view
	public void deleteProduct(Product m) {
		try {
			connection.createStatement()
					.execute("DELETE FROM `monark_my_cart` WHERE " + "product_id='" + m.getId() + "'");
			connection.createStatement().execute("DELETE FROM `monark_product` WHERE " + "id='" + m.getId() + "'");
			connection.commit();
		} catch (SQLException ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ex.getMessage() + ", in delete in db: monark_product");
		}
	}

	//Create class which create update details query for admin view
	public void update(Product m) {

		try {
			if (m != null) {
				connection.createStatement()
						.execute("UPDATE `monark_product` SET `name`='" + m.getName() + "', `price`='" + m.getPrice()
								+ "',`brand`='" + m.getBrand() + "' where " + "id='" + m.getId() + "'");
				connection.commit();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage() + ", in insert in db: monark_product");
		}
	}
}