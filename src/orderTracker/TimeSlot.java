package orderTracker;

public class TimeSlot {

	private String time;
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
		System.out.println(time + ", " + customerName);
	}
	
	public void AddCustomer(String name) {
		customerName = name;
	}
	
	public void RemoveCustomer() {
		customerName = null;
	}
}
