package ordertracker_old;

public class TimeSlot {

    private final String time;
    private String customerName;
    private final String meridiem;
	
    public TimeSlot(String t, String cN, String m) {
        time = t;
	customerName = cN;
	meridiem = m;
    }
	
    public TimeSlot(String t, String m) {
    	time = t;
	customerName = null;
	meridiem = m;
    }
	
    public String GetTime() { return time; }
    public String GetCustomerName() { return customerName; }
    public String GetMeridiem() { return meridiem; }
	
    public void PrintTimeSlot() {
	System.out.print(time + meridiem + ", " + customerName);
    }
	
    public String WriteTimeSlot() {
	String cn = "null";
	
	if (customerName != null) {
            cn = customerName.replace(' ', ';');
	}
		
	return time + " " + meridiem + " " + cn + "\n";
    }
	
    public void AddCustomer(String name) {
	customerName = name;
    }
	
    public void RemoveCustomer() {
	customerName = null;
    }
}
