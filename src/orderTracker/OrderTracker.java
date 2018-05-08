// ---------------------------------------------------------- //
/*	BUG REPORT:
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
			String[] data = cons.RunInterfaceConsole(todaysOrders, tomorrowsOrders);
			
			if (data.length == 4) {
				
				// Iterate through list, find time slot, if customer name is not null, continue, if null, set customer name, if all time slots have been traversed
				// then print message saying over booking is not allowed
				boolean added = false;
				
				if (data[3].equals("today")) {
					for (TimeSlot slot : todaysOrders) {
						if (slot.GetTime().equals(data[1]) && slot.GetMeridiem().equals(data[2])) {		
							if (slot.GetCustomerName() == null) {
								slot.AddCustomer(data[0]);
								added = true;
								break;
							}			
						}					
					}
				} else {
					for (TimeSlot slot : tomorrowsOrders) {
						if (slot.GetTime().equals(data[1]) && slot.GetMeridiem().equals(data[2])) {		
							if (slot.GetCustomerName() == null) {
								slot.AddCustomer(data[0]);
								added = true;
								break;
							}			
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
					PrintWriter writerToday = new PrintWriter("persistantDataToday.txt", "UTF-8");
					
					for (TimeSlot slot : todaysOrders) {
						writerToday.println(slot.WriteTimeSlot());
					}
					
					writerToday.close();
					
					PrintWriter writerTomorrow = new PrintWriter("persistantDataTomorrow.txt", "UTF-8");
					
					for (TimeSlot slot : tomorrowsOrders) {
						writerTomorrow.println(slot.WriteTimeSlot());
					}
					
					writerTomorrow.close();
					
					return;
					
				}
				else {
					// Iterate through list, find element with customer name, set it to null
					boolean found = false;
					
					for (TimeSlot slot : todaysOrders) {
						if (slot.GetCustomerName() != null && slot.GetCustomerName().equals(data[0])) {						
							slot.RemoveCustomer();
							found = true;
							break;
						}
					}
					
					if (found == false) {
						for (TimeSlot slot : tomorrowsOrders) {
							if (slot.GetCustomerName() != null && slot.GetCustomerName().equals(data[0])) {						
								slot.RemoveCustomer();
								found = true;
								break;
							}
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
