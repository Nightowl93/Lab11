package co.grandcircus.Lab11;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarApp {

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		ArrayList<Car> allCars = new ArrayList<>();
		
		int numCars = 0;
		int j;
		String make;
		String model;
		int year = 0;
		double price = -1;
		boolean keepGoing = true;
		boolean invalidYear = true;
		boolean invalidPrice = true;
		
		System.out.println("Welcome to the Facebook Java AutoShop admin console!");
			
		do {
			System.out.println("How many cars will you be entering?");
			
			try {
				numCars = scnr.nextInt();
				if (numCars <= 0) {
					throw new IllegalArgumentException();
				}
			}
			catch(InputMismatchException ex) {
				System.out.println("Error: Please enter a valid numeric value.");
				scnr.nextLine();
				continue;
			}
			catch(IllegalArgumentException ex) {
				System.out.println("Error: Please enter a valid numeric value, greater than 0.");
				scnr.nextLine();
				continue;
			}
			
			keepGoing = false;
		}
		while (keepGoing);
	
		
		for (int i = 0; i < numCars; i++) {
			scnr.nextLine();
			
			j = i + 1;
			
			System.out.println("Enter Car #" + j + " Make: ");
			make = scnr.nextLine();
							
			System.out.println("Enter Car #" + j + " Model: ");
			model = scnr.nextLine();
			
			do {
			
				try {
					System.out.println("Enter Car #" + j + " Year: ");
					year = scnr.nextInt();
					 if (year < 1000 || year >= 10000) {
						 throw new IllegalArgumentException();
					 }
				}
				catch(InputMismatchException ex) {
					System.out.println("Invalid Year. Please try again in format yyyy.");
					scnr.nextLine();
					continue;
				}
				catch(IllegalArgumentException ex) {
					System.out.println("Invalid Year. Year must be a positive value in format yyyy.");
					scnr.nextLine();
					continue;
				}
				
				invalidYear = false;
			}
			while(invalidYear);
			
			do {
				
				try {
					System.out.println("Enter Car #" + j + " Price: ");
					price = scnr.nextDouble();
					
					if (price < 0) {
						throw new IllegalArgumentException();
					}
				}
				catch(InputMismatchException ex) {
					System.out.println("Invalid Price. Please enter a numeric value, with no symbols.");
					scnr.nextLine();
					continue;
				}
				catch(IllegalArgumentException ex) {
					System.out.println("Invalid Price. Price cannot be below 0. Please try again.");
					scnr.nextLine();
					continue;
				}
				
				invalidPrice = false;
			}
			while(invalidPrice);
						
			System.out.println();
			
			allCars.add(new Car(make, model, year, price));
		}
		
		System.out.println("Current Inventory: \n");
		
		String format = "%-20s %-20s %-20s %-20s";
		
		System.out.printf(format, "Make", "Model", "Year", "Price");
		System.out.println();
		System.out.printf(format, "========", "========", "========", "========"); 
		System.out.println();
		
		
		//pre-formatted my Car.toString method to print in same format as above
		for(Car car : allCars) {
			System.out.println(car.toString());
		}
			
		
		
		scnr.close();
	}
	
	//Maybe add in try/catch exceptions for the int year and double price?
	//Maybe unwrap it from the do while loop since it only needs to happen once!
	//Change it so that there are only small do while loops that encompass just the 3 try catches I'll have to include.

}
