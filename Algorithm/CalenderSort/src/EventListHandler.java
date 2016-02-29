import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;


public class EventListHandler{


	private StaticEventList staticList;
	private DynamicEventList dynamicList;
	private ArrayList<CalendarEvent> events; //list to store all events in one given day


	public EventListHandler() {}

	public ArrayList<CalendarEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<CalendarEvent> events) {
		this.events = events;
	}
/*
	public ArrayList<CalendarEvent> getEventsByDate (String dateKey) throws CalendarError {
		if (dateKey == null)
			throw new CalendarError("Null Event");

		events = new ArrayList<CalendarEvent>();
		ArrayList<StaticEvent> staticArrayList = staticList.getList();
		if (staticArrayList != null){
			for(int i=0; i<staticArrayList.size(); i++){
				if(staticArrayList.get(i).getDateKey().contains(dateKey)){
					events.add(staticArrayList.get(i));
				}
			}}
		ArrayList<DynamicEvent> dynamicArrayList = dynamicList.getList();
		if (dynamicArrayList != null){
			for(int i=0; i<dynamicArrayList.size(); i++){
				if(dynamicArrayList.get(i).getDateKey().contains(dateKey)){
					events.add(dynamicArrayList.get(i));
				}
			}}

		return events;
	}
*/
	public void initStaticList(){
		staticList = new StaticEventList();
	}

	public void initDynamicList(){
		dynamicList = new DynamicEventList();
	}

	public StaticEventList getStaticList() {
		return staticList;
	}

	public void setStaticList(StaticEventList staticList) {
		this.staticList = staticList;
	}

	public DynamicEventList getDynamicList() {
		return dynamicList;
	}

	public void setDynamicList(DynamicEventList dynamicList) {
		this.dynamicList = dynamicList;
	}


	public boolean checkValidTime(Calendar startTime, Calendar endTime){
		if (startTime.compareTo(endTime) >=0){
			return false;
		}
		return true;
	}

	//Create a static event to add to the static event list
	public boolean createStaticEvent(String name, String location, Calendar startTime, Calendar endTime,
			boolean isStatic, boolean isPeriodic, boolean isFinished, String description, String color) throws CalendarError{
		//check if start and end times are valid
		boolean check = false;
		if(!checkValidTime(startTime, endTime)){
			System.out.println("Fail");
			return false;
		}
		//check if event is static
		if (isStatic == false)
			return false;

		StaticEvent staticEvent = new StaticEvent(name, location, startTime, endTime, isStatic, 
				isPeriodic, isFinished, description, color);
		staticEvent.setId(System.currentTimeMillis());
		check = staticList.addEvent(staticEvent);
		if(!check)
			System.out.println("Fail");
		return (check);
	}



	public boolean removeEventById(int temp) throws CalendarError{
		boolean check = true;
		if (staticList == null){
			check = false;
			return check;
		}
		check = staticList.removeEventById(temp);
		return check;
	};

	//Create a dynamic event to add to the dynamic event list
	public void createDynamicEvent(String name, int estimatedLength, boolean isStatic,
			Calendar deadline, boolean isFinished, String description){
		return;
	}



	//Dynamic sort algorithm
	public boolean dynamicSort(){

		Comparator<StaticEvent> staticcomparator = new Comparator<StaticEvent>(){

			@Override
			public int compare(StaticEvent o1, StaticEvent o2) {
				return o1.getStartTime().compareTo(o2.getStartTime());
			}
		};

		Comparator<DynamicEvent> comparator = new Comparator<DynamicEvent>(){

			@Override
			public int compare(DynamicEvent o1, DynamicEvent o2) {
				return o1.getDeadline().compareTo(o2.getDeadline());
			}
		};

		Comparator<DynamicEvent> reversecomparator = new Comparator<DynamicEvent>(){

			@Override
			public int compare(DynamicEvent o1, DynamicEvent o2) {
				return -o1.getDeadline().compareTo(o2.getDeadline());
			}
		};

		PriorityQueue<DynamicEvent> currDynamicEList = new PriorityQueue<DynamicEvent>(comparator);
		PriorityQueue<DynamicEvent> reverseDynamicEList = new PriorityQueue<DynamicEvent>(reversecomparator);
		PriorityQueue<StaticEvent> currStaticEList = new PriorityQueue<StaticEvent>(staticcomparator);
		PriorityQueue<StaticEvent> sortedStaticEList = new PriorityQueue<StaticEvent>(staticcomparator);
		PriorityQueue<StaticEvent> freeList = new PriorityQueue<StaticEvent>(); 
		ArrayList<StaticEvent> staticArrayList = staticList.getList();
		ArrayList<DynamicEvent> dynamicArrayList = null;
		if(dynamicList!=null){
			dynamicArrayList = dynamicList.getList();
		}
		
		if(staticArrayList != null)
		{
			for(int i=0; i<staticArrayList.size();i++){
				if(!staticArrayList.get(i).isFinished()){
					currStaticEList.add(staticArrayList.get(i));
				}
			}}
		if(dynamicArrayList != null){
			for(int i=0; i<dynamicArrayList.size();i++){
				if(!dynamicArrayList.get(i).isFinished()){
					currDynamicEList.add(dynamicArrayList.get(i));
				}
			}}
		while (!currStaticEList.isEmpty()){
			DateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = currStaticEList.poll().getStartTime().getTime();
			System.out.println(time.format(date));
			
		}
		return false;
	}

	/*checks if there are conflicts in the static time table, if there are conflicts, return the two conflicted
	events as a single event*/
	private void checkConflict(PriorityQueue<StaticEvent> currStaticEList, PriorityQueue<StaticEvent> sortedStaticEList) 
			throws CalendarError{
		while(!currStaticEList.isEmpty()){
			StaticEvent firstCheck = currStaticEList.poll();
			StaticEvent secondCheck = null;
			if(!currStaticEList.isEmpty()){
				secondCheck = currStaticEList.peek();
			}
			if(firstCheck.getEndTime().compareTo(secondCheck.getStartTime()) <=0){
				StaticEvent newevent = new StaticEvent(firstCheck.getName(), 
						firstCheck.getLocation(), firstCheck.getStartTime(), secondCheck.getEndTime(),
						firstCheck.isStatic(), firstCheck.isPeriodic(), firstCheck.isFinished(),
						firstCheck.getDescription(), firstCheck.getColor());
				sortedStaticEList.add(newevent);
			}	
		}
	}
	
	private int daysBetween(Calendar d1, Calendar d2) {
        return (int) (Math.abs(d2.getTime().getTime() - d1.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

	//returns false if no free time, true will write freetime to freeList
	private boolean updateFreeTime(PriorityQueue<StaticEvent> sortedStaticEList,
			PriorityQueue<DynamicEvent> reverseDynamicEvent, PriorityQueue<StaticEvent> freeList) throws CalendarError{
		
		//get deadline from last of currDynamicEvent
		DynamicEvent lastdynamicevent = reverseDynamicEvent.peek();
		
		Calendar currTime = Calendar.getInstance();
		Calendar lastDynamicTime = lastdynamicevent.getEndTime();
		
		
		//make freeTime block
		Calendar startTime = null;
		Calendar endTime = null;
		StaticEvent freeBlock = null;
		int days = this.daysBetween(lastDynamicTime, currTime);
		for (int i=0; i<=days; i++){
			if (i==0){
				startTime = currTime;
			}

			else if(i==days){
				endTime = lastDynamicTime;
			}
			else {
			startTime = Calendar.getInstance();
			startTime.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,9, 0);
			endTime = Calendar.getInstance();
			endTime.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH,21, 0);
			}
			
			freeBlock = new StaticEvent("free time", "null", startTime, endTime,
			true, false, false, "null", "null");
			freeList.add(freeBlock);
			startTime.add(Calendar.DAY_OF_MONTH, 1);

		}

		while(!sortedStaticEList.isEmpty())
		{
			StaticEvent temp = sortedStaticEList.peek();
			if ((freeList.peek().getStartTime()).compareTo(temp.getEndTime()) <=0){
				
			}
		}
		
		
		
		return true;
	}


}

/*
ArrayList<StaticEvent>() events = EventListHandler.getStaticEventsByDateKey(string dateKey); ...DONE
ArrayList<DynamicEvent>() events = EventListHandler.getDynamicEventsByDateKey(string dateKey);
StaticEvent se;
string id = se.getId();
EventHandler.removeEventById(id); ...DONE

setEventFinished(String Id)
addColor field to staticEvent.... Done
 */