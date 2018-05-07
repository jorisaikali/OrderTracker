package orderTracker;
import java.util.Scanner;

public class ConsoleBased {
	
	Scanner reader = new Scanner(System.in);
	
	public String[] RunInterfaceConsole() {
		
		System.out.println("Welcome to the Order Tracker.\n\n Please choose one of the following options.");
		System.out.println("\n 1) Print today's orders.\n 2) Print tomorrow's orders.\n 3) Add a customer's name to a time slot.\n 4) Complete an order with a customers name.\n 5) Quit.");
		
		while (true) {
			System.out.println("\nEnter a number: ");
			int n = reader.nextInt();
			reader.nextLine();
			
			String customerName, timeSlot;
			
			switch(n) {
				case 1:
					// Print today's orders
					break;
					
				case 2:
					// Print tomorrow's orders
					break;
			
				case 3:
					customerName = GetCustomerNameConsole();
					String slot[] = GetTimeSlot();
					
					timeSlot = slot[0];
					String meridiem = slot[1];
					
					return new String[] {customerName, timeSlot, meridiem};
					
				case 4:
					customerName = GetCustomerNameConsole();
					
					return new String[] {customerName};
					
				case 5:
					
					return new String[] {"EXIT"};
					
				default:
					System.out.println("Please input a number between 1 to 3.");
			}
					
		}
	}
	
	public void SuccessAdd() { System.out.println("\nCustomer added successfully.\n"); }
	public void FailedAdd() { System.out.println("\nThere are already too many orders for this timeslot. Addition of customer failed.\n"); }
	
	private String GetCustomerNameConsole() {
		System.out.println("\nWhat is the customer's name?");
		String customerName = reader.nextLine();
		
		return customerName;
	}
	
	private String[] GetTimeSlot() {
		System.out.println("\nWhich time slot? (hh:mm AM/PM)");
		String slot = reader.nextLine();
		String[] splitSlot = slot.split(" ");
		
		String timeSlot = splitSlot[0];
		String meridiem = splitSlot[1];
		
		return new String[] { timeSlot, meridiem };
	}
}
