package orderTracker;

public class OrderTracker {
	
	private TimeSlot[] initTimeSlots() {
		
		TimeSlot[] timeSlots = {new TimeSlot("8:00"), new TimeSlot("8:00"), new TimeSlot("8:00"), 
								new TimeSlot("8:30"), new TimeSlot("8:30"), new TimeSlot("8:30"),
								new TimeSlot("9:00"), new TimeSlot("9:00"), new TimeSlot("9:00"),
								new TimeSlot("9:30"), new TimeSlot("9:30"), new TimeSlot("9:30"),
								new TimeSlot("10:00"), new TimeSlot("10:00"), new TimeSlot("10:00"),
								new TimeSlot("10:30"), new TimeSlot("10:30"), new TimeSlot("10:30"),
								new TimeSlot("11:00"), new TimeSlot("11:00"), new TimeSlot("11:00"),
								new TimeSlot("11:30"), new TimeSlot("11:30"), new TimeSlot("11:30"),
								new TimeSlot("12:00"), new TimeSlot("12:00"), new TimeSlot("12:00"),
								new TimeSlot("12:30"), new TimeSlot("12:30"), new TimeSlot("12:30"),
								new TimeSlot("1:00"), new TimeSlot("1:00"), new TimeSlot("1:00"),
								new TimeSlot("1:30"), new TimeSlot("1:30"), new TimeSlot("1:30"),
								new TimeSlot("2:00"), new TimeSlot("2:00"), new TimeSlot("2:00"),
								new TimeSlot("2:30"), new TimeSlot("2:30"), new TimeSlot("2:30"),
								new TimeSlot("3:00"), new TimeSlot("3:00"), new TimeSlot("3:00"),
								new TimeSlot("3:30"), new TimeSlot("3:30"), new TimeSlot("3:30"),
								new TimeSlot("4:00"), new TimeSlot("4:00"), new TimeSlot("4:00"),
								new TimeSlot("4:30"), new TimeSlot("4:30"), new TimeSlot("4:30"),
								new TimeSlot("5:00"), new TimeSlot("5:00"), new TimeSlot("5:00"),
								new TimeSlot("5:30"), new TimeSlot("5:30"), new TimeSlot("5:30"),
								new TimeSlot("6:00"), new TimeSlot("6:00"), new TimeSlot("6:00"),
								new TimeSlot("6:30"), new TimeSlot("6:30"), new TimeSlot("6:30"),
								new TimeSlot("7:00"), new TimeSlot("7:00"), new TimeSlot("7:00"),
								new TimeSlot("7:30"), new TimeSlot("7:30"), new TimeSlot("7:30"),
								new TimeSlot("8:00"), new TimeSlot("8:00"), new TimeSlot("8:00"),
								new TimeSlot("8:30"), new TimeSlot("8:30"), new TimeSlot("8:30") };
		
		return timeSlots;
	}
	
	public static void main(String[] args) {
		
		OrderTracker ot = new OrderTracker();
		
		TimeSlot[] todaysOrders = ot.initTimeSlots();
		TimeSlot[] tomorrowsOrders = ot.initTimeSlots();
		
	}

}
