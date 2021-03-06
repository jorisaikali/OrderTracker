package ordertracker;

public class TimeSlot {

    private final String time;
    private String customerName;
	
    public TimeSlot(String t, String cN) {
        time = t;
	customerName = cN;
    }
	
    public TimeSlot(String t) {
    	time = t;
	customerName = null;
    }
	
    public String GetTime() { return time; }
    public String GetCustomerName() { return customerName; }
    
    public void PrintTimeSlot() {
	System.out.print(time + ", " + customerName);
    }
	
    public String WriteTimeSlot() {
	String cn = "null";
	
	if (customerName != null) {
            cn = customerName.replace(' ', ';');
	}
		
	return time + " " + cn + "\n";
    }
	
    public void AddCustomer(String name) {
	customerName = name;
    }
	
    public void RemoveCustomer() {
	customerName = null;
    }
}
