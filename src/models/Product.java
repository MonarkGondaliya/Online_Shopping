package models;

//Create Product class which create all setter getter method for product
public class Product {
	private String name, brand;
	private Integer id, price;

	public Product(Integer id, String name, String brand, Integer price) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public Integer getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Material [name=" + name + ", brand=" + brand + ", id=" + id + ", price=" + price + "]";
	}

}
