import java.util.ArrayList;

public class DynamicEventList implements CalendarObjectList<ArrayList<DynamicEvent>, DynamicEvent> {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DynamicEvent> dynamicList; // an ArrayList to store the dynamic events after they are sorted

	
	public ArrayList<DynamicEvent> getList() {
		return dynamicList;
	}

	public void setList(ArrayList<DynamicEvent> list) {
		this.dynamicList = list;
	}

	public boolean addEvent(DynamicEvent event) throws CalendarError {

		if (event == null)
			throw new CalendarError("Null Event");

		this.dynamicList.add(event);
		return true;
	}

    public boolean removeEventById(String id) throws CalendarError {
    	boolean check = false;
		if (id == null)
			throw new CalendarError("Null Event");
		DynamicEvent eventToRemove;
		if(dynamicList == null) return check;
		for (int i = 0; i< dynamicList.size(); i++){
			eventToRemove = dynamicList.get(i);
			  if (eventToRemove.getId().contains(id)){
			    dynamicList.remove(eventToRemove);
			    check = true;
			  }
		}
		return check;
	}
    
}