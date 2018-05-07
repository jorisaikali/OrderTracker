// ---------------------------------------------------------- //
/*	BUG REPORT:
 * 		- After adding customer "test" to 4:00 PM, when trying to complete "test", fails at line 99 in OrderTracker.java; if (slot.GetCustomerName().equals(data[0])) {
 * 
 * 
 */
// ---------------------------------------------------------- //

package orderTracker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class OrderTracker {
	
	private TimeSlot[] initTimeSlots() {
		
		TimeSlot[] timeSlots = {new TimeSlot("8:00", "Jori", "AM"), new TimeSlot("8:00", "Bob", "AM"), new TimeSlot("8:00", "Kelsey", "AM"), 
								new TimeSlot("8:30", "AM"), new TimeSlot("8:30", "AM"), new TimeSlot("8:30", "AM"),
								new TimeSlot("9:00", "AM"), new TimeSlot("9:00", "AM"), new TimeSlot("9:00", "AM"),
								new TimeSlot("9:30", "AM"), new TimeSlot("9:30", "AM"), new TimeSlot("9:30", "AM"),
								new TimeSlot("10:00", "AM"), new TimeSlot("10:00", "AM"), new TimeSlot("10:00", "AM"),
								new TimeSlot("10:30", "AM"), new TimeSlot("10:30", "AM"), new TimeSlot("10:30", "AM"),
								new TimeSlot("11:00", "AM"), new TimeSlot("11:00", "AM"), new TimeSlot("11:00", "AM"),
								new TimeSlot("11:30", "AM"), new TimeSlot("11:30", "AM"), new TimeSlot("11:30", "AM"),
								new TimeSlot("12:00", "PM"), new TimeSlot("12:00", "PM"), new TimeSlot("12:00", "PM"),
								new TimeSlot("12:30", "PM"), new TimeSlot("12:30", "PM"), new TimeSlot("12:30", "PM"),
								new TimeSlot("1:00", "PM"), new TimeSlot("1:00", "PM"), new TimeSlot("1:00", "PM"),
								new TimeSlot("1:30", "PM"), new TimeSlot("1:30", "PM"), new TimeSlot("1:30", "PM"),
								new TimeSlot("2:00", "PM"), new TimeSlot("2:00", "PM"), new TimeSlot("2:00", "PM"),
								new TimeSlot("2:30", "PM"), new TimeSlot("2:30", "PM"), new TimeSlot("2:30", "PM"),
								new TimeSlot("3:00", "PM"), new TimeSlot("3:00", "PM"), new TimeSlot("3:00", "PM"),
								new TimeSlot("3:30", "PM"), new TimeSlot("3:30", "PM"), new TimeSlot("3:30", "PM"),
								new TimeSlot("4:00", "PM"), new TimeSlot("4:00", "PM"), new TimeSlot("4:00", "PM"),
								new TimeSlot("4:30", "PM"), new TimeSlot("4:30", "PM"), new TimeSlot("4:30", "PM"),
								new TimeSlot("5:00", "PM"), new TimeSlot("5:00", "PM"), new TimeSlot("5:00", "PM"),
								new TimeSlot("5:30", "PM"), new TimeSlot("5:30", "PM"), new TimeSlot("5:30", "PM"),
								new TimeSlot("6:00", "PM"), new TimeSlot("6:00", "PM"), new TimeSlot("6:00", "PM"),
								new TimeSlot("6:30", "PM"), new TimeSlot("6:30", "PM"), new TimeSlot("6:30", "PM"),
								new TimeSlot("7:00", "PM"), new TimeSlot("7:00", "PM"), new TimeSlot("7:00", "PM"),
								new TimeSlot("7:30", "PM"), new TimeSlot("7:30", "PM"), new TimeSlot("7:30", "PM"),
								new TimeSlot("8:00", "PM"), new TimeSlot("8:00", "PM"), new TimeSlot("8:00", "PM"),
								new TimeSlot("8:30", "PM"), new TimeSlot("8:30", "PM"), new TimeSlot("8:30", "PM") };
		
		return timeSlots;
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		OrderTracker ot = new OrderTracker();
		ConsoleBased cons = new ConsoleBased();
		
		TimeSlot[] todaysOrders = ot.initTimeSlots();
		TimeSlot[] tomorrowsOrders = ot.initTimeSlots();
		
		while(true) {
			String[] data = cons.RunInterfaceConsole();
			
			if (data.length == 3) {
				
				// Iterate through list, find time slot, if customer name is not null, continue, if null, set customer name, if all time slots have been traversed
				// then print message saying over booking is not allowed
				boolean added = false;
				
				for (TimeSlot slot : todaysOrders) {
					if (slot.GetTime().equals(data[1]) && slot.GetMeridiem().equals(data[2])) {		
						if (slot.GetCustomerName() == null) {
							slot.AddCustomer(data[0]);
							added = true;
							break;
						}			
					}					
				}
				
				if (added) {
					cons.SuccessAdd();
				} else {
					cons.FailedAdd();
				}
				
			} else if (data.length == 1) {
				
				if (data[0] == "EXIT") {
					// Write all elements from list to file
					PrintWriter writer = new PrintWriter("persistantData.txt", "UTF-8");
					
					for (TimeSlot slot : todaysOrders) {
						writer.println(slot.WriteTimeSlot());
					}
					
					writer.close();
					return;
					
				}
				else {
					// Iterate through list, find element with customer name, set it to null
					for (TimeSlot slot : todaysOrders) {
						if (slot.GetCustomerName().equals(data[0])) {						
							slot.RemoveCustomer();
							break;
						}
					}
				}
				
			} else {
				System.out.println("Error has occurred.");
				return;
			}
		}
		
	}

}
