import java.util.PriorityQueue;

public class CalendarEventList{

	private PriorityQueue<CalendarEvent> list;

	public CalendarEventList() {
		this.list = new PriorityQueue<CalendarEvent>();
	}
	
	public PriorityQueue<CalendarEvent> getList() {
		return list;
	}

	public void setList(PriorityQueue<CalendarEvent> list) {
		this.list = list;
	}
	
	public void insertEvent(CalendarEvent event) throws CalendarError {
		
		if (event == null)
			throw new CalendarError("Null Event");
		
		this.list.add(event);
	}
	
	
	
	
}
