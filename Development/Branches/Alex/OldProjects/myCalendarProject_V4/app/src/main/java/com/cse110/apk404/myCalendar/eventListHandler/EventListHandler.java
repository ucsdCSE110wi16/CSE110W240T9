package com.cse110.apk404.myCalendar.eventListHandler;

import java.text.DateFormat;
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
	

	public boolean checkValidTime(CalendarDate startTime, CalendarDate endTime){
		if (startTime.getYear() != endTime.getYear() || startTime.getMonth() != endTime.getMonth() || 
				startTime.getDay() != endTime.getDay() ||
				endTime.getHour() - startTime.getHour() <= 0){
			//System.out.println(startTime.getHour() - endTime.getHour());
			return false;
		}
		return true;
	}
	
	//Create a static event to add to the static event list
	public boolean createStaticEvent(String name, String location, CalendarDate startTime, CalendarDate endTime,
			boolean isStatic, boolean isPeriodic, boolean isFinished, String description, String color) throws CalendarError{
		//check if start and end times are valid
		boolean check = false;
		Random randomno = new Random();
		if(!checkValidTime(startTime, endTime)){
			System.out.println("Fail");
			return false;
		}
		//check if event is static
		if (isStatic == false)
			return false;
		//set the DateKey String used for getting all the events in one day
		String dateKey = startTime.DateKey();
		StaticEvent staticEvent = new StaticEvent(dateKey, name, location, startTime, endTime, isStatic, 
				isPeriodic, isFinished, description, color);
		staticEvent.setId(Integer.parseInt(staticEvent.getStartTime().getMonth() + staticEvent.getStartTime().getDay() + "" +
				staticEvent.getStartTime().getHour() + "" + randomno.nextInt(9999)));
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
			CalendarDate deadline, boolean isFinished, String description){
	    return;
	}

	
	
//	//Dynamic sort algorithm
//	public boolean dynamicSort(){
//		
//		Comparator<StaticEvent> staticcomparator = new Comparator<StaticEvent>(){
//
//			@Override
//			public int compare(StaticEvent o1, StaticEvent o2) {
//				return Long.compare(o1.getStartTime().time(), o2.getStartTime().time());
//			}
//				
//		};
//		
//		Comparator<DynamicEvent> comparator = new Comparator<DynamicEvent>(){
//
//			@Override
//			public int compare(DynamicEvent o1, DynamicEvent o2) {
//				return Long.compare(o1.getDeadline().time(), o2.getDeadline().time());
//			}
//		};
//		
//		Comparator<DynamicEvent> reversecomparator = new Comparator<DynamicEvent>(){
//
//			@Override
//			public int compare(DynamicEvent o1, DynamicEvent o2) {
//				return Long.compare(o2.getDeadline().time(), o1.getDeadline().time());
//			}
//				
//		};
//				
//		PriorityQueue<DynamicEvent> currDynamicEList = new PriorityQueue<DynamicEvent>(comparator);
//		PriorityQueue<DynamicEvent> reverseDynamicEList = new PriorityQueue<DynamicEvent>(reversecomparator);
//		PriorityQueue<StaticEvent> currStaticEList = new PriorityQueue<StaticEvent>(staticcomparator);
//		ArrayList<StaticEvent> staticArrayList = staticList.getList();
//		ArrayList<DynamicEvent> dynamicArrayList = null;
//		if(dynamicList!=null){
//		dynamicArrayList = dynamicList.getList();}
//		ArrayList<StaticEvent> freeList = new ArrayList<StaticEvent>(); 
//		if(staticArrayList != null){
//		for(int i=0; i<staticArrayList.size();i++){
//			if(!staticArrayList.get(i).isFinished()){
//				currStaticEList.add(staticArrayList.get(i));
//			}
//		}}
//		if(dynamicArrayList != null){
//		for(int i=0; i<dynamicArrayList.size();i++){
//			if(!dynamicArrayList.get(i).isFinished()){
//				currDynamicEList.add(dynamicArrayList.get(i));
//			}
//		}}
//		while (!currStaticEList.isEmpty()){
//			System.out.println(currStaticEList.poll().getStartTime().time());
//		}
//		return false;
//	}
	
//	private PriorityQueue<StaticEvent> checkConflict(PriorityQueue<StaticEvent> currStaticEList){
//		return null;
//	}
	
//	private PriorityQueue<StaticEvent> updateFreeTime(PriorityQueue<StaticEvent> currStaticEList,
//			PriorityQueue<DynamicEvent> reverseDynamicEvent){
//		//get deadline from last of currDynamicEvent
//		DynamicEvent lastdynamicevent = reverseDynamicEvent.peek();
//		ArrayList<StaticEvent> freeList = new ArrayList<StaticEvent>(); 
//		int lasteventyear = lastdynamicevent.getDeadline().getYear();
//		int lasteventmonth = lastdynamicevent.getDeadline().getMonth();
//		int lasteventday = lastdynamicevent.getDeadline().getDay();
//		DateFormat year = new SimpleDateFormat("yyyy");
//		DateFormat month = new SimpleDateFormat("MM");
//		DateFormat day = new SimpleDateFormat("dd");
//		DateFormat hour = new SimpleDateFormat("HH");
//		DateFormat minute = new SimpleDateFormat("mm");
//		Date date = new Date();
//		int curryear = Integer.parseInt(year.format(date));
//		int currmonth = Integer.parseInt(month.format(date));
//		int currday = Integer.parseInt(day.format(date));
//		int currhour = Integer.parseInt(hour.format(date));
//		int currminute = Integer.parseInt(minute.format(date));
//		if(lasteventyear < curryear || lasteventmonth < currmonth || lasteventday < currday)
//			return new
//		PriorityQueue<StaticEvent> freeList = new PriorityQueue<StaticEvent>();
//		//while(currStatic)
//				return null;
//	}
//	
//
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