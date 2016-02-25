import java.util.ArrayList;

public class StaticEventList implements CalendarEventList{

	private ArrayList<StaticEvent> list; //list of all static events
	private ArrayList<StaticEvent> events; //list to store all events in one given day
	
	public StaticEventList() {
		this.list = new ArrayList<StaticEvent>();
	}
	
	public ArrayList<StaticEvent> getList() {
		return list;
	}
	
	public void clearEvents(){
		events.clear();
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
	
    public ArrayList<StaticEvent> addEventList(String dateKey) throws CalendarError {
		if (dateKey == null)
			throw new CalendarError("Null Event");
		for (StaticEvent eventToAdd : list){
			  if (eventToAdd.getDateKey().contains(dateKey)){
			    events.add(eventToAdd);
			  }
		}
		return events;
	}
    
    public boolean removeEventById(String id) throws CalendarError {
    	boolean check = false;
		if (id == null)
			throw new CalendarError("Null Event");
		for (StaticEvent eventToRemove : list){
			  if (eventToRemove.getId().contains(id)){
			    events.remove(eventToRemove);
			    check = true;
			  }
		}
		return check;
	}
}
