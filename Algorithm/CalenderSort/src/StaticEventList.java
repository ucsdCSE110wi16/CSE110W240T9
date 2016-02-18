import java.util.ArrayList;

public class StaticEventList{

	private ArrayList<StaticEvent> list;
	private ArrayList<StaticEvent> events;
	
	public StaticEventList() {
		this.list = new ArrayList<StaticEvent>();
	}
	
	public ArrayList<StaticEvent> getList() {
		return list;
	}

	public void setList(ArrayList<StaticEvent> list) {
		this.list = list;
	}

	public boolean addEvent(StaticEvent event) throws CalendarError {
		boolean check = true;
		if (event == null){
			check = false;
			throw new CalendarError("Null Event");
		}
		this.list.add(event);
		return check;
	}
	
//	public boolean removeEvent(String Id) throws CalendarError{
//		boolean check = true;
//		if (event == null){
//			check = false;
//			throw new CalendarError("Null Event");
//		}
//		list.remove(event);
//		return check;
//	}

	public ArrayList<StaticEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<StaticEvent> events) {
		this.events = events;
	}
	
    public ArrayList<StaticEvent> addEventList(String key) throws CalendarError {
		if (key == null)
			throw new CalendarError("Null Event");
		for (StaticEvent eventToAdd : list){
			  if (eventToAdd.getId().contains(key)){
			    events.add(eventToAdd);
			  }
		}
		return events;
	}
    
    public ArrayList<StaticEvent> removeEventList(String key) throws CalendarError {
		if (key == null)
			throw new CalendarError("Null Event");
		for (StaticEvent eventToRemove : events){
			  if (eventToRemove.getId().contains(key)){
			    events.remove(eventToRemove);
			  }
		}
		return events;
	}
}
