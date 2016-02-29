import java.util.ArrayList;
<<<<<<< HEAD

public class DynamicEventList{


	private ArrayList<DynamicEvent> dynamicList; // an ArrayList to store the dynamic events after they are sorted

=======
import java.util.PriorityQueue;

public class DynamicEventList{

	private PriorityQueue<DynamicEvent> list; //a pq to hold all the events
	private PriorityQueue<Slot> timeBlock; // a pq to hold all the free time blocks
	private ArrayList<DynamicEvent> storage; // an ArrayList to store the dynamic events after they are sorted
>>>>>>> dfa1fce35586f406121b47a9e23909eb089dfbee
	

	public ArrayList<DynamicEvent> getList() {
		return dynamicList;
	}

	public void setList(ArrayList<DynamicEvent> list) {
		this.dynamicList = list;
	}
    

	public void addEvent(DynamicEvent event) throws CalendarError {

		if (event == null)
			throw new CalendarError("Null Event");

		this.dynamicList.add(event);
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


<<<<<<< HEAD
=======
	public PriorityQueue<Slot> getTimeBlock() {
		return timeBlock;
	}


	public void setTimeBlock(PriorityQueue<Slot> timeBlock) {
		this.timeBlock = timeBlock;
	}
	
	public Slot getFreeTime(){
		return timeBlock.poll();
	}

	public ArrayList<DynamicEvent> getStorage() {
		return storage;
	}

	public void setStorage(ArrayList<DynamicEvent> storage) {
		this.storage = storage;
	}
>>>>>>> dfa1fce35586f406121b47a9e23909eb089dfbee
}
