package co.grandcircus.Lab11;

public class Car {

	private String make;
	private String model;
	private int year;
	private double price;
	
	public Car(String make, String model, int year, double price) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
	}
	
	public Car() {
		
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		String format = "%-25s %-25s %-25s %-45s";
		return String.format(format, make, model, year, "$" + price);
	}
	
	
}
