/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Jori El-Saikali
 */
public class OrderTrackerModel {
    
    private TimeSlot[] todaysOrders;
    private TimeSlot[] tomorrowsOrders;
    private final String todayFilename = "data/persistantDataToday.txt";
    private final String tomorrowFilename = "data/persistantDataTomorrow.txt";
    
    public OrderTrackerModel() {
        this.todaysOrders = initTimeSlots();
        this.tomorrowsOrders = initTimeSlots();
    }
    
    public TimeSlot[] getTodaysOrders() { return this.todaysOrders; }
    public TimeSlot[] getTomorrowsOrders() { return this.tomorrowsOrders; }
    
    public boolean addCustomer(String customerName, String todayOrTomorrow, String time) {
        
        boolean added = false;
				
        if (todayOrTomorrow.equals("Today")) {
            for (TimeSlot slot : this.todaysOrders) {
                if (slot.GetTime().equals(time)) {		
                    if (slot.GetCustomerName() == null || slot.GetCustomerName().equals("null")) {
                        slot.AddCustomer(customerName);
                        added = true;
                        break;
                    }			
		}					
            }
        } else {
            for (TimeSlot slot : this.tomorrowsOrders) {
                if (slot.GetTime().equals(time)) {		
                    if (slot.GetCustomerName() == null || slot.GetCustomerName().equals("null")) {
                        slot.AddCustomer(customerName);
			added = true;
			break;
                    }			
		}					
            }
	}
        
        return added;
    }
    
    public boolean removeCustomer(String customerName) {
        boolean found = false;
					
        for (TimeSlot slot : this.todaysOrders) {
            if (slot.GetCustomerName() != null && slot.GetCustomerName().equals(customerName)) {						
                slot.RemoveCustomer();
                found = true;
                break;
            }
        }
					
        if (found == false) {
            for (TimeSlot slot : this.tomorrowsOrders) {
                if (slot.GetCustomerName() != null && slot.GetCustomerName().equals(customerName)) {						
                    slot.RemoveCustomer();
                    found = true;
                    break;
                }
            }
        }
                    
        return found;
    }
    
    public boolean checkOrderMax(String time, String todayOrTomorrow) {             
        if (todayOrTomorrow.equals("Today")) {
            return checkMax(todaysOrders, time);
        }
        else {
            return checkMax(tomorrowsOrders, time);
        }
    }
    
    public void writePersistantData() throws FileNotFoundException, UnsupportedEncodingException {
        // Write all elements from list to file
        PrintWriter writerToday = new PrintWriter("data/persistantDataToday.txt", "UTF-8");
					
        for (TimeSlot slot : this.todaysOrders) {
            writerToday.println(slot.WriteTimeSlot());
        }
					
        writerToday.close();
					
        PrintWriter writerTomorrow = new PrintWriter("data/persistantDataTomorrow.txt", "UTF-8");
					
        for (TimeSlot slot : this.tomorrowsOrders) {
            writerTomorrow.println(slot.WriteTimeSlot());
        }
					
        writerTomorrow.close();
    }
    
    public boolean openPersistantData() {
        String line = null;
        
        try {
            FileReader fileReaderToday = new FileReader(todayFilename);
            BufferedReader bufferedReader = new BufferedReader(fileReaderToday);
            
            int count = 0;
            
            processFile(line, bufferedReader, this.todaysOrders, count);
            count = 0;
            bufferedReader.close();
			
            FileReader fileReaderTomorrow = new FileReader(tomorrowFilename);
            bufferedReader = new BufferedReader(fileReaderTomorrow);
	
            processFile(line, bufferedReader, this.tomorrowsOrders, count);
			
            bufferedReader.close();
            return true;
        }
        catch(FileNotFoundException ex) {
            return false;
        }
	catch(IOException ex) {
            return false;
	}
        
        /* CODE MAY BE NEEDED IN THE FUTURE IF PROCESSFILE() DOESN'T WORK
            while((line = bufferedReader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }

                String[] splitLines = line.split(" ");
                String name = splitLines[2].replace(';', ' ');

                if (name.equals("null") == false) {
                    this.todaysOrders[count].AddCustomer(name);
                }
                else {
                    count++;
                    continue;
                }

                count++;
            }
        */
    }
    
    private TimeSlot[] initTimeSlots() {
		
        TimeSlot[] timeSlots = {new TimeSlot("8:00 AM"), new TimeSlot("8:00 AM"), new TimeSlot("8:00 AM"), 
				new TimeSlot("8:30 AM"), new TimeSlot("8:30 AM"), new TimeSlot("8:30 AM"),
				new TimeSlot("9:00 AM"), new TimeSlot("9:00 AM"), new TimeSlot("9:00 AM"),
				new TimeSlot("9:30 AM"), new TimeSlot("9:30 AM"), new TimeSlot("9:30 AM"),
				new TimeSlot("10:00 AM"), new TimeSlot("10:00 AM"), new TimeSlot("10:00 AM"),
				new TimeSlot("10:30 AM"), new TimeSlot("10:30 AM"), new TimeSlot("10:30 AM"),
				new TimeSlot("11:00 AM"), new TimeSlot("11:00 AM"), new TimeSlot("11:00 AM"),
				new TimeSlot("11:30 AM"), new TimeSlot("11:30 AM"), new TimeSlot("11:30 AM"),
				new TimeSlot("12:00 PM"), new TimeSlot("12:00 PM"), new TimeSlot("12:00 PM"),
				new TimeSlot("12:30 PM"), new TimeSlot("12:30 PM"), new TimeSlot("12:30 PM"),
				new TimeSlot("1:00 PM"), new TimeSlot("1:00 PM"), new TimeSlot("1:00 PM"),
				new TimeSlot("1:30 PM"), new TimeSlot("1:30 PM"), new TimeSlot("1:30 PM"),
				new TimeSlot("2:00 PM"), new TimeSlot("2:00 PM"), new TimeSlot("2:00 PM"),
				new TimeSlot("2:30 PM"), new TimeSlot("2:30 PM"), new TimeSlot("2:30 PM"),
				new TimeSlot("3:00 PM"), new TimeSlot("3:00 PM"), new TimeSlot("3:00 PM"),
				new TimeSlot("3:30 PM"), new TimeSlot("3:30 PM"), new TimeSlot("3:30 PM"),
				new TimeSlot("4:00 PM"), new TimeSlot("4:00 PM"), new TimeSlot("4:00 PM"),
				new TimeSlot("4:30 PM"), new TimeSlot("4:30 PM"), new TimeSlot("4:30 PM"),
				new TimeSlot("5:00 PM"), new TimeSlot("5:00 PM"), new TimeSlot("5:00 PM"),
				new TimeSlot("5:30 PM"), new TimeSlot("5:30 PM"), new TimeSlot("5:30 PM"),
				new TimeSlot("6:00 PM"), new TimeSlot("6:00 PM"), new TimeSlot("6:00 PM"),
				new TimeSlot("6:30 PM"), new TimeSlot("6:30 PM"), new TimeSlot("6:30 PM"),
				new TimeSlot("7:00 PM"), new TimeSlot("7:00 PM"), new TimeSlot("7:00 PM"),
				new TimeSlot("7:30 PM"), new TimeSlot("7:30 PM"), new TimeSlot("7:30 PM"),
				new TimeSlot("8:00 PM"), new TimeSlot("8:00 PM"), new TimeSlot("8:00 PM"),
				new TimeSlot("8:30 PM"), new TimeSlot("8:30 PM"), new TimeSlot("8:30 PM") };
		
            return timeSlots;
	}
    
    private void processFile(String line, BufferedReader bufferedReader, TimeSlot[] slots, int count) throws IOException {
        while((line = bufferedReader.readLine()) != null) {		
            if (line.equals("")) {
                continue;
            }
				
            String[] splitLines = line.split(" ");
            String name = splitLines[2].replace(';', ' ');
				
            if (name != null) {
                slots[count].AddCustomer(name);
            }
            else {
                count++;
                continue;
            }
				
            count++;
        }
    }
    
    private boolean checkMax(TimeSlot[] slots, String time) {
        TimeSlot currSlot = null;
        int index = 0, count = 0;
        
        for (TimeSlot slot : slots) {
            currSlot = slot;
            if (slot.GetTime().equals(time)) {
                break;
            }
                
            index++;
        }
        
        if (currSlot.GetTime().contains("00")) {
            for (int i = index; i < index + 6; i++) {
                System.out.println("i: " + i);
                
                if (slots[i].GetCustomerName() != null) {
                    count++;
                }
            }
        }
        else if (currSlot.GetTime().contains("30")) {
            for (int i = index - 3; i < index + 3; i++) {
                if (slots[i].GetCustomerName() != null) {
                    count++;
                }
            }
        }
        
        return count == 3;
    }
    
}