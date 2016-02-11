import java.util.PriorityQueue;

public class DynamicEventList{

	private PriorityQueue<DynamicEvent> list;

	public DynamicEventList(){
		this.list = new PriorityQueue<DynamicEvent>();
	}
	

	public PriorityQueue<DynamicEvent> getList() {
		return list;
	}

	public void setList(PriorityQueue<DynamicEvent> list) {
		this.list = list;
	}

	public void addEvent(DynamicEvent event) throws CalendarError {

		if (event == null)
			throw new CalendarError("Null Event");

		this.list.add(event);
	}
	public void sortEvent(){

	}
	public void removeEvent(){}
	
}
