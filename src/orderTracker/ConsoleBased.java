package orderTracker;
import java.util.Scanner;

public class ConsoleBased {
	
	Scanner reader = new Scanner(System.in);
	
	public String[] RunInterfaceConsole() {
		
		System.out.println("Welcome to the Order Tracker.\n\n Please choose one of the following options.");
		System.out.println("\n 1) Add a customer's name to a time slot.\n 2) Complete an order with a customers name.\n 3) Quit.");
		
		while (true) {
			System.out.println("\nEnter a number: ");
			int n = reader.nextInt();
			
			String customerName, timeSlot;
			
			switch(n) {
				case 1:
					customerName = GetCustomerNameConsole();
					timeSlot = GetTimeSlot();
					
					return new String[] {customerName, timeSlot};
					
				case 2:
					customerName = GetCustomerNameConsole();
					
					return new String[] {customerName};
					
				case 3:
					
					return new String[] {"EXIT"};
					
				default:
					System.out.println("Please input a number between 1 to 3.");
			}
					
		}
	}
	
	private String GetCustomerNameConsole() {
		System.out.println("\nWhat is the customer's name?");
		String customerName = reader.next();
		
		return customerName;
	}
	
	private String GetTimeSlot() {
		System.out.println("\nWhich time slot?");
		String timeSlot = reader.next();
		
		return timeSlot;
	}
}
