/*Shontinique Uqdah
 * July 30, 2018
 */
package co.grandcircus.Lab11;

public class UsedCar extends Car {

	private double mileage;
	
	public UsedCar(String make, String model, int year, double price, double mileage) {
		super(make, model, year, price);
		this.mileage = mileage;
	}
	
	public UsedCar() {
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		String format = "%-25s %-25s %-25s %-25s";
		return String.format(format, getMake(), getModel(), getYear(), "$" + getPrice() + " (Used) " + mileage + " miles");
	}
	
	
}
