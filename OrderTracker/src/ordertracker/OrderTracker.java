// ---------------------------------------------------------- //
/*	BUG REPORT:
 *
 */
// ---------------------------------------------------------- //

package ordertracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class OrderTracker {
	
    private TimeSlot[] initTimeSlots() {
		
        TimeSlot[] timeSlots = {new TimeSlot("8:00", "AM"), new TimeSlot("8:00", "AM"), new TimeSlot("8:00", "AM"), 
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
        Home gfx = new Home();
        gfx.RunGFX();
		
	TimeSlot[] todaysOrders = ot.initTimeSlots();
	TimeSlot[] tomorrowsOrders = ot.initTimeSlots();
		
	String todayFileName = "data/persistantDataToday.txt";
	String tomorrowFileName = "data/persistantDataTomorrow.txt";
		
	String line = null;
		
	try {
            FileReader fileReaderToday = new FileReader(todayFileName);
			
            BufferedReader bufferedReader = new BufferedReader(fileReaderToday);
			
            int count = 0;
            //System.out.println("C1");
            while((line = bufferedReader.readLine()) != null) {
                // Process line and insert to array

                if (line.equals("")) {
                    continue;
                }

                String[] splitLines = line.split(" ");
                String name = splitLines[2].replace(';', ' ');

                if (name.equals("null") == false) {
                    todaysOrders[count].AddCustomer(name);
                }
                else {
                    count++;
                    continue;
                }

                count++;
            }
			
            count = 0;
            bufferedReader.close();
			
            FileReader fileReaderTomorrow = new FileReader(tomorrowFileName);
            bufferedReader = new BufferedReader(fileReaderTomorrow);
			
            while((line = bufferedReader.readLine()) != null) {
            	// Process line and insert to array
				
		if (line.equals("")) {
                    continue;
		}
				
		String[] splitLines = line.split(" ");
		String name = splitLines[2].replace(';', ' ');
				
		if (name != null) {
                    tomorrowsOrders[count].AddCustomer(name);
		}
		else {
                    count++;
                    continue;
		}
				
		count++;
            }
			
            bufferedReader.close();
	} 
	catch(FileNotFoundException ex) {
            System.out.println("Unable to open file. Creating new ones.");
            }
	catch(IOException ex) {
            System.out.println("Error reading file.");
	}
		
	while(true) {
            String[] data = cons.RunInterfaceConsole(todaysOrders, tomorrowsOrders);
			
            if (data.length == 4) {
				
		// Iterate through list, find time slot, if customer name is not null, continue, if null, set customer name, if all time slots have been traversed
		// then print message saying over booking is not allowed
		boolean added = false;
				
		if (data[3].equals("today")) {
                    for (TimeSlot slot : todaysOrders) {
			if (slot.GetTime().equals(data[1]) && slot.GetMeridiem().equals(data[2])) {		
                            if (slot.GetCustomerName() == null || slot.GetCustomerName().equals("null")) {
				slot.AddCustomer(data[0]);
				added = true;
				break;
                            }			
			}					
                    }
                } else {
                    for (TimeSlot slot : tomorrowsOrders) {
			if (slot.GetTime().equals(data[1]) && slot.GetMeridiem().equals(data[2])) {		
                            if (slot.GetCustomerName() == null || slot.GetCustomerName().equals("null")) {
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
                    PrintWriter writerToday = new PrintWriter("data/persistantDataToday.txt", "UTF-8");
					
                    for (TimeSlot slot : todaysOrders) {
                    	writerToday.println(slot.WriteTimeSlot());
                    }
					
                    writerToday.close();
					
                    PrintWriter writerTomorrow = new PrintWriter("data/persistantDataTomorrow.txt", "UTF-8");
					
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
					
                    if (found == false) {
			System.out.println(data[0] + " was not found.\n");
                    }
		}
				
            } else {
                System.out.println("Error has occurred.");
		return;
            }
	}
		
    }

}
