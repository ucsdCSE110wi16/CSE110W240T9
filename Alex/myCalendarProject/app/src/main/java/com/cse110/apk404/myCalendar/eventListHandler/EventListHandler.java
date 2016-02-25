package com.cse110.apk404.myCalendar.eventListHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

public class EventListHandler{

	private ObjectInputStream objIn; 
	private StaticEventList staticList;
	private DynamicEventList dynamicList;

	public void EventListInputStream(String filename) throws IOException {
		FileInputStream fi = new FileInputStream(filename);
		this.objIn = new ObjectInputStream(fi);
	}
	
	public StaticEventList readStaticEventList() {
		StaticEventList staticList = null; 
		try {
			staticList = (StaticEventList) this.objIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	    return staticList;
	}

	
	
	public DynamicEventList readDynamicEventList() {
		DynamicEventList dynamicList = null; 
		try {
			this.setDynamicList((DynamicEventList) this.objIn.readObject());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	    return dynamicList;
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
		if (startTime.getYear() != endTime.getYear() || startTime.getMonth() != endTime.getMonth() || 
				startTime.getDay() != endTime.getDay() ||
				startTime.getHour() - endTime.getHour() <= 0){
			return false;
		}
		return true;
	}
	
	//Create a static event to add to the static event list
	public boolean createStaticEvent(String name, String location, Calendar startTime, Calendar endTime,
			boolean isStatic, boolean isPeriodic, boolean isFinished, String description, String color) throws CalendarError{
		//check if start and end times are valid
		boolean checkvalid = checkValidTime(startTime, endTime);
		//check if event is static
		if (isStatic == false)
			return false;
		//set the DateKey String used for getting all the events in one day
		String dateKey = startTime.DateKey();
		StaticEvent staticEvent = new StaticEvent(dateKey, name, location, startTime, endTime, isStatic, 
				isPeriodic, isFinished, description, color);
		staticEvent.setId(dateKey + name + startTime.getHour() + startTime.getMinute());
		boolean check = staticList.addEvent(staticEvent);
		return (check && checkvalid);
	}
	
	public void clear(){
		staticList.clearEvents();
	}
	
	public boolean removeEventById(String Id) throws CalendarError{
		boolean check = true;
		check = staticList.removeEventById(Id);
		return check;
	};
	
	//Create a dynamic event to add to the dynamic event list
	public void createDynamicEvent(){
		
	}
	
	//Dynamic sort algorithm
	public void dynamicSort(){}
	
	//key is date
	public ArrayList<StaticEvent> getStaticEventsByKey(String key) throws CalendarError{
		return staticList.addEventList(key);
	}
	
	public ArrayList<DynamicEvent> getDynamicEventsByKey(String key){
		return null;
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