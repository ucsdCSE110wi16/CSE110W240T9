//import java.util.ArrayList;
//import java.util.PriorityQueue;
//
//public class DynamicEventList{
//
//	private PriorityQueue<DynamicEvent> list; //a pq to hold all the events
//	private PriorityQueue<Calendar> timeBlock; // a pq to hold all the free time blocks
//	private ArrayList<DynamicEvent> storage; // an ArrayList to store the dynamic events after they are sorted
//	
//
//	public PriorityQueue<DynamicEvent> getList() {
//		return list;
//	}
//
//	public void setList(PriorityQueue<DynamicEvent> list) {
//		this.list = list;
//	}
//    
//	public DynamicEvent getDynamicEvent(){
//		return list.poll();
//	}
//	public void addEvent(DynamicEvent event) throws CalendarError {
//
//		if (event == null)
//			throw new CalendarError("Null Event");
//
//		this.list.add(event);
//	}
//
//	public void removeEvent(DynamicEvent event) throws CalendarError{
//		if (event == null)
//			throw new CalendarError("Null Event");
//		list.remove(event);
//	}
//
//
//	public PriorityQueue<Calendar> getTimeBlock() {
//		return timeBlock;
//	}
//
//
//	public void setTimeBlock(PriorityQueue<Calendar> timeBlock) {
//		this.timeBlock = timeBlock;
//	}
//	
//	public Calendar getFreeTime(){
//		return timeBlock.poll();
//	}
//
//	public ArrayList<DynamicEvent> getStorage() {
//		return storage;
//	}
//
//	public void setStorage(ArrayList<DynamicEvent> storage) {
//		this.storage = storage;
//	}
//}
