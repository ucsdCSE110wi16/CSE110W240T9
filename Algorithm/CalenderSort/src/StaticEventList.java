import java.util.ArrayList;

public class StaticEventList{

	private ArrayList<StaticEvent> list;

	public StaticEventList() {
		this.list = new ArrayList<StaticEvent>();
	}
	
	public ArrayList<StaticEvent> getList() {
		return list;
	}

	public void setList(ArrayList<StaticEvent> list) {
		this.list = list;
	}
	
	public void addEvent(StaticEvent event) throws CalendarError {
		
		if (event == null)
			throw new CalendarError("Null Event");
		
		this.list.add(event);
	}
	
	public void removeEvent(){}
	
}
