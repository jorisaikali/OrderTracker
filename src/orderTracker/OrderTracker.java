package orderTracker;

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
	
	public static void main(String[] args) {
		
		OrderTracker ot = new OrderTracker();
		ConsoleBased cons = new ConsoleBased();
		
		TimeSlot[] todaysOrders = ot.initTimeSlots();
		TimeSlot[] tomorrowsOrders = ot.initTimeSlots();
		
		String[] data = cons.RunInterfaceConsole();
		
	}

}
