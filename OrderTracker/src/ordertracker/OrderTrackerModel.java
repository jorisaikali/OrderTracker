/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author Jori El-Saikali
 */
public class OrderTrackerModel {
    
    private ArrayList<TimeSlot> todaysOrders;
    private ArrayList<TimeSlot> tomorrowsOrders;
    private final String todayFilename = "persistentDataToday.txt";
    private final String tomorrowFilename = "persistentDataTomorrow.txt";
    private final String appDataDirectory;
    private File path;
    private File dataPath;
    private int settingsOrderMax;
    private TimeSlot[] slotHelper = {new TimeSlot("8:00 AM"), new TimeSlot("8:30 AM"), new TimeSlot("9:00 AM"),
                                     new TimeSlot("9:30 AM"), new TimeSlot("10:00 AM"), new TimeSlot("10:30 AM"),
                                     new TimeSlot("11:00 AM"), new TimeSlot("11:30 AM"), new TimeSlot("12:00 PM"),
                                     new TimeSlot("12:30 PM"), new TimeSlot("1:00 PM"), new TimeSlot("1:30 PM"),
                                     new TimeSlot("2:00 PM"), new TimeSlot("2:30 PM"), new TimeSlot("3:00 PM"), 
                                     new TimeSlot("3:30 PM"), new TimeSlot("4:00 PM"), new TimeSlot("4:30 PM"),
                                     new TimeSlot("5:00 PM"), new TimeSlot("5:30 PM"), new TimeSlot("6:00 PM"),
                                     new TimeSlot("6:30 PM"), new TimeSlot("7:00 PM"), new TimeSlot("7:30 PM"),
                                     new TimeSlot("8:00 PM"), new TimeSlot("8:30 PM")};;
    //private String settingsPath;
    
    public OrderTrackerModel() {             
        File config = new File("config.ini");
        String line;
        
        try {
            FileReader fileReaderConfig = new FileReader(config);
            BufferedReader bufferedReader = new BufferedReader(fileReaderConfig);
            
            while((line = bufferedReader.readLine()) != null) {		
                if (line.equals("")) {
                    continue;
                }

                String[] splitLines = line.split(" ");
                
                if (splitLines[0].equals("max")) {
                    this.settingsOrderMax = Integer.parseInt(splitLines[1]);
                }
                /*
                else if (splitLines[0].equals("path")) {
                    this.settingsPath = splitLines[1];
                }
                */
            }
            
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            
        }
	catch(IOException ex) {
            
	}
        
        this.todaysOrders = initTimeSlots();
        this.tomorrowsOrders = initTimeSlots();
        
        /*
        if (this.settingsPath.contains("APPDATA")) {
            this.appDataDirectory = System.getenv("APPDATA");
        } else {
            this.appDataDirectory = this.settingsPath.replace("/", "\\");
        }
        */
        
        this.appDataDirectory = System.getenv("APPDATA");
        
        this.path = new File(appDataDirectory + "\\OrderTracker");
        this.path.mkdir();
        this.dataPath = new File(path.toString() + "\\data");
        this.dataPath.mkdir();
    }
    
    public ArrayList<TimeSlot> getTodaysOrders() { return this.todaysOrders; }
    public ArrayList<TimeSlot> getTomorrowsOrders() { return this.tomorrowsOrders; }
    public int getSettingsMax() { return this.settingsOrderMax; }
    //public String getFinalPath() { return this.appDataDirectory + "\\OrderTracker\\data"; }
    
    public void setSettingsMax(int max) { this.settingsOrderMax = max; }
    
    public boolean hasCustomer(String customerName) {
        				
        for (TimeSlot slot : this.todaysOrders) {
            if (slot.GetCustomerName() != null && slot.GetCustomerName().equals(customerName)) {						
                return true;
            }
        }
					
        for (TimeSlot slot : this.tomorrowsOrders) {
            if (slot.GetCustomerName() != null && slot.GetCustomerName().equals(customerName)) {						
                return true;
            }
        }
             
        return false;
    }
    
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
    
    public boolean checkOrderMax(String time, String todayOrTomorrow, int max) {
        this.settingsOrderMax = max;
        
        if (todayOrTomorrow.equals("Today")) {
            return checkMax(todaysOrders, time);
        }
        else {
            return checkMax(tomorrowsOrders, time);
        }
    }
    
    public TimeSlot[] getRow(String time, String todayOrTomorrow) {
        
        if (todayOrTomorrow.equals("Today")) {
            for (int i = 0; i < this.todaysOrders.size(); i++) {
                if (this.todaysOrders.get(i).GetTime().equals(time)) {
                    TimeSlot[] row = { this.todaysOrders.get(i), this.todaysOrders.get(i+1), this.todaysOrders.get(i+2)};
                    return row;
                }
            }
        }
        else {
            for (int i = 0; i < this.tomorrowsOrders.size(); i++) {
                if (this.tomorrowsOrders.get(i).GetTime().equals(time)) {
                    TimeSlot[] row = { this.tomorrowsOrders.get(i), this.tomorrowsOrders.get(i+1), this.tomorrowsOrders.get(i+2)};
                    return row;
                }
            }
        }
        
        return null;
    }
    
    public void shiftTimeSlots() {
        this.todaysOrders = (ArrayList<TimeSlot>)this.tomorrowsOrders.clone();
        this.tomorrowsOrders = initTimeSlots();     
    }
    
    public void writePersistantData() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        // Write all elements from list to file
        File todaysFile = new File(dataPath, todayFilename);
        
        todaysFile.setReadable(true, false);
        todaysFile.setWritable(true, false);

        try (PrintWriter writerToday = new PrintWriter(todaysFile)) {
            for (TimeSlot slot : this.todaysOrders) {
                writerToday.println(slot.WriteTimeSlot());
            }
            
            todaysFile.setReadable(false, false);
            todaysFile.setWritable(false, false);
        }
	
        File tomorrowsFile = new File(dataPath, tomorrowFilename);
        
        tomorrowsFile.setReadable(true, false);
        tomorrowsFile.setWritable(true, false);
        
        try (PrintWriter writerTomorrow = new PrintWriter(tomorrowsFile)) {
            for (TimeSlot slot : this.tomorrowsOrders) {
                writerTomorrow.println(slot.WriteTimeSlot());
            }
            
            tomorrowsFile.setReadable(false, false);
            tomorrowsFile.setWritable(false, false);
        }
    }
    
    public boolean openPersistantData() {
        String line = null;
        
        try {
            FileReader fileReaderToday = new FileReader(dataPath + "/" + todayFilename);
            BufferedReader bufferedReader = new BufferedReader(fileReaderToday);
            
            int count = 0;
            
            processFile(line, bufferedReader, this.todaysOrders, count);
            count = 0;
            bufferedReader.close();
			
            FileReader fileReaderTomorrow = new FileReader(dataPath + "/" + tomorrowFilename);
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
    }
    
    public void writeConfigFile() throws FileNotFoundException {
        File configFile = new File("config.ini");
        
        System.out.println("settingsOrderMax: " + this.settingsOrderMax);
        
        try (PrintWriter configWriter = new PrintWriter(configFile)) {
            configWriter.println("max " + String.valueOf(this.settingsOrderMax));
        }
    }
    
    private ArrayList<TimeSlot> initTimeSlots() {
		
        ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		
        int index = 0;
        for (int i = 0; i < slotHelper.length * this.settingsOrderMax; i++) {
            if ((i % this.settingsOrderMax) == 0) {
                index++;
            }
            
            TimeSlot slot = new TimeSlot(slotHelper[index-1].GetTime());
            timeSlots.add(slot);
        }

        return timeSlots;
    }
    
    private TimeSlot[] addTimeSlotHelper() {
        
        /*
        for (int i = 0; i < slots.length; i++) {
            if ((i % this.settingsOrderMax) == 0) {
                
            }
        }
        */

        return null;
    }
    
    private void processFile(String line, BufferedReader bufferedReader, ArrayList<TimeSlot> slots, int count) throws IOException {
        while((line = bufferedReader.readLine()) != null) {		
            if (line.equals("")) {
                continue;
            }
				
            String[] splitLines = line.split(" ");
            String name = splitLines[2].replace(';', ' ');
            
            if (name != null) {
                slots.get(count).AddCustomer(name);
            }
            else {
                count++;
                continue;
            }
				
            count++;
        }
    }
    
    private boolean checkMax(ArrayList<TimeSlot> slots, String time) {
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
                if (slots.get(i).GetCustomerName() != null && !"null".equals(slots.get(i).GetCustomerName())) {
                    count++;
                }
            }
        }
        else if (currSlot.GetTime().contains("30")) {
            for (int i = index - 3; i < index + 3; i++) {
                if (slots.get(i).GetCustomerName() != null && !"null".equals(slots.get(i).GetCustomerName())) {
                    count++;
                }
            }
        }

        System.out.println("count: " + count + ", settingsOrderMax: " + this.settingsOrderMax);
        
        return count >= this.settingsOrderMax;
    }
    
}
