/*Shontinique Uqdah
 * July 30, 2018
 */

package co.grandcircus.Lab11;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllCarApp {

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		String userType;
		boolean startOver = true;
		
		ArrayList<Car> allCars = new ArrayList<>();
		stockCars(allCars);
		
		
		System.out.println("Welcome to the Facebook Java AutoShop!");
		
		while (startOver) {		
			System.out.println("Are you an authorized admin or a potential buyer? Enter \"A\" for admin or \"B\" for buyer: ");
			userType = scnr.nextLine().trim();
			
			if (userType.matches("[Aa]")) {
				
				editCars(scnr, allCars);
				//removed a scanner here
				startOver = keepGoing(scnr);
				
			}
			
			else if (userType.matches("[Bb]")) {
				buyCars(scnr, allCars);
				startOver = keepGoing(scnr);
			}
			
		}
		
		
		System.out.println("Thank you for visiting Facebook Java AutoShop! Goodbye :)");
		
		
		
		scnr.close();
	}
	
	public static void stockCars(ArrayList<Car> allCars) {
		allCars.add(new Car("Lincoln", "Navigator", 2018, 75000));
		allCars.add(new UsedCar("Ford", "Explorer", 2003, 5200, 107021));
		allCars. add(new UsedCar("Kia", "Optima", 2015, 19789, 29800));
		allCars.add(new Car("Hyundai", "Sonata", 2017, 37987));
		allCars.add(new UsedCar("Ford", "Fusion", 2007, 8432.57, 88400));
		allCars.add(new Car ("Buick", "LaCrosse", 2017, 25483.32));
	}

	public static void editCars(Scanner scnr, ArrayList<Car> allCars) {
		
		String userChoice;
		String addType;
		
		System.out.println("Welcome to the admin console!");
		
		System.out.println("Would you like to ADD or REMOVE cars from the lot?");
		userChoice = scnr.nextLine();
		
		Pattern add = Pattern.compile("add", Pattern.CASE_INSENSITIVE);
		Matcher addMatch = add.matcher(userChoice);
		
		Pattern remove = Pattern.compile("remove", Pattern.CASE_INSENSITIVE);
		Matcher removeMatch = remove.matcher(userChoice);
		
		if (removeMatch.find()) {
			
			removeCars(scnr, allCars);
			
		}
		
		
		if (addMatch.find()) {
			
			System.out.println("Would you like to add NEW or USED cars?");
			addType = scnr.nextLine().trim();
			
			if (addType.matches("[Nn][Ee][Ww]")) {
				
				addNewCars(scnr, allCars);
				
			}
			
			else {
				
				addUsedCars(scnr, allCars);
			}
			
			
			
		}
		
		showInventory(allCars);
		
		
	}
	
	public static void showInventory(ArrayList<Car> allCars) {
			int count = 1;
			System.out.println("Current Inventory: \n");
			
			String format = "%-28s %-25s %-25s %-20s";
			
			System.out.printf(format, "Make", "Model", "Year", "Price");
			System.out.println();
			System.out.printf(format, "========", "========", "========", "========"); 
			System.out.println();
			
			
			//pre-formatted my Car.toString method to print in same format as above
			for(Car car : allCars) {
				System.out.println(count + ". " + car.toString());
				count++;
			}
			
		}

		
	public static void addNewCars(Scanner scnr, ArrayList<Car> allCars) {
			int numCars = 0;
			int j;
			String make;
			String model;
			int year = 0;
			double price = -1;
			boolean keepGoing;
			boolean invalidYear;
			boolean invalidPrice;
			
			do {
				keepGoing = true;
				invalidYear = true;
				invalidPrice = true;
				
				System.out.println("How many NEW cars will you be entering?");
				
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
				
				scnr.nextLine();
			}
		}

	public static void addUsedCars(Scanner scnr, ArrayList<Car> allCars) {
		int numCars = 0;
		int j;
		String make;
		String model;
		int year = 0;
		double price = -1;
		double mileage = -1;
		boolean keepGoing;
		boolean invalidYear;
		boolean invalidPrice;
		boolean invalidMileage;
		
		do {
			keepGoing = true;
			invalidYear = true;
			invalidPrice = true;
			invalidMileage = true;
			
			System.out.println("How many USED cars will you be entering?");
			
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
						
			do {
				
				try {
					System.out.println("Enter Car #" + j + " Mileage: ");
					mileage = scnr.nextDouble();
					
					if (mileage < 0) {
						throw new IllegalArgumentException();
					}
				}
				catch(InputMismatchException ex) {
					System.out.println("Invalid Mileage. Please enter a numeric value, with no symbols.");
					scnr.nextLine();
					continue;
				}
				catch(IllegalArgumentException ex) {
					System.out.println("Invalid Mileage. Mileage cannot be below 0. Please try again.");
					scnr.nextLine();
					continue;
				}
				
				invalidMileage = false;
			}
			while(invalidMileage);
			System.out.println();
			
			allCars.add(new UsedCar(make, model, year, price, mileage));
			
			
			scnr.nextLine();
		}
	}
	
	public static void removeCars(Scanner scnr, ArrayList<Car> allCars) {
			
			int carChoice = 0;
			int numCars = allCars.size() + 1;
			boolean keepGoing = true;
			String removeCar;
			String userResponse = null;
			boolean browseMore = true;
			
			do {
				showInventory(allCars);
				
				System.out.printf("%-20s", numCars + ". Exit");
				System.out.println();
				
				do {
					System.out.println("Which car would you like to remove from the lot? (Enter the number of your desired car): ");
					
					try {
						carChoice = scnr.nextInt();
						if (carChoice < 0 || carChoice > numCars) {
							throw new IllegalArgumentException();
						}
					}
					catch(InputMismatchException ex) {
						System.out.println("Invalid Entry. Must enter valid non-negative numeric value.");
						continue;
					}
					catch(IllegalArgumentException ex) {
						System.out.println("That car does not exist. Must enter valid non-negative numeric value between 1 and " + numCars + ".");
						continue;
					}
					
					keepGoing = false;
				}
				while(keepGoing);
				
				if (carChoice == numCars) {
					System.out.println("No changes have been made.");
					browseMore = false;
					scnr.nextLine();
				}
				
				else {
					System.out.println(allCars.get(carChoice - 1).toString());
					System.out.println("Would you like to remove this car from the lot? (y/n): ");
					
					//just added, maybe delete
					scnr.nextLine();
					removeCar = scnr.nextLine().toLowerCase();
					
					if (removeCar.startsWith("y")) {
						System.out.println("Thank you for updating the inventory! The car has been successfully removed.");
						allCars.remove(carChoice - 1);
					}
					
					else {
						System.out.println("Okay! No changes have been made to the lot.");
					}
					
					System.out.println("Would you like to continue updating the lot? (y/n)");
					userResponse = scnr.nextLine().trim().toLowerCase();
					
					if (userResponse.startsWith("y")) {
						browseMore = true;
					}
					
					else {
						browseMore = false;
						System.out.println("Thank you for updating the inventory!");
					}
				}
			}
			while (browseMore);
		
		}
		
		//Still need to be able to add a used car!!
		
	public static void buyCars(Scanner scnr, ArrayList<Car> allCars) {
			int carChoice = 0;
			int numCars; 
			boolean keepGoing;
			String buyCar;
			String userResponse = null;
			boolean browseMore;
			
			System.out.println("Awesome! Let's browse our available cars.");
			
			do {
			keepGoing = true;
			browseMore = true;
			
			
			showInventory(allCars);
			numCars = allCars.size() + 1;
			
			System.out.printf("%-20s", numCars + ". Exit");
			System.out.println();
			
			do {
				System.out.println("Which car would you like? (Enter the number of your desired car): ");
				
				try {
					carChoice = scnr.nextInt();
					if (carChoice < 0 || carChoice > numCars) {
						throw new IllegalArgumentException();
					}
				}
				catch(InputMismatchException ex) {
					System.out.println("Invalid Entry. Must enter valid non-negative numeric value.");
					scnr.nextLine();
					continue;
				}
				catch(IllegalArgumentException ex) {
					System.out.println("That car does not exist. Must enter valid non-negative numeric value between 1 and " + numCars + ".");
					scnr.nextLine();
					continue;
				}
				
				keepGoing = false;
			}
			while(keepGoing);
			
			if (carChoice == numCars) {
				System.out.println("Thank you for browsing our car lot!");
				browseMore = false;
				scnr.nextLine();
			}
			
			else {
				System.out.println(allCars.get(carChoice - 1).toString());
				System.out.println("Would you like to buy this car? (y/n): ");
				scnr.nextLine();
				buyCar = scnr.nextLine().toLowerCase();
				
				if (buyCar.startsWith("y")) {
					System.out.println("Wonderful! A sales agent will contact you shortly to finalize the sale!");
					allCars.remove(carChoice - 1);
				}
				
				else {
					System.out.println("Okay! Maybe next time.");
				}
				
				System.out.println("Would you like to continue browsing? (y/n)");
				userResponse = scnr.nextLine().trim().toLowerCase();
				
				if (userResponse.startsWith("y")) {
					browseMore = true;
				}
				
				else {
					browseMore = false;
					System.out.println("Thank you for browsing our car lot!");
				}
			}
			}
			while (browseMore);
			
		}
		
	public static boolean keepGoing(Scanner scnr) {
			String nextAction;
			
			System.out.println("Do you wish to continue or exit? Please enter \"A\" to contiue or \"B\" to exit: ");
			nextAction = scnr.nextLine().trim();
			
			if (nextAction.matches("[Aa]")) {
				return true;
			}
			
			else {
				return false;
				
			}
			
		}
}
