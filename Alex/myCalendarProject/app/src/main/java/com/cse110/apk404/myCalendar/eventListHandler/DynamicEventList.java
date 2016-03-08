package com.cse110.apk404.myCalendar.eventListHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DynamicEventList implements CalendarObjectList<ArrayList<DynamicEvent>, DynamicEvent> {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<DynamicEvent> dynamicList; // an ArrayList to store the dynamic events after they are sorted

	public DynamicEventList(){
		dynamicList = new ArrayList<DynamicEvent>();
	}
	
	public ArrayList<DynamicEvent> getList() {
		return dynamicList;
	}

	public void setList(ArrayList<DynamicEvent> list) {
		this.dynamicList = list;
	}

	public boolean addEvent(DynamicEvent event) throws CalendarError {
		boolean check = true;
		if (event == null){
			check = false;
			throw new CalendarError("Null Event");
		}

		this.dynamicList.add(event);
		return true;
	}

    public boolean removeEventById(Long id) throws CalendarError {
    	boolean check = false;
		if (id == null)
			throw new CalendarError("Null Event");
		DynamicEvent eventToRemove;
		if(dynamicList == null) return check;
		for (int i = 0; i< dynamicList.size(); i++){
			eventToRemove = dynamicList.get(i);
			  if (eventToRemove.getId() == id){
			    dynamicList.remove(eventToRemove);
			    check = true;
			  }
		}
		return check;
	}
    
    public void print(){
    	DynamicEvent de;
    	Date start;
    	Date end;
    	DateFormat date;
    	int size = dynamicList.size();
    	System.out.println("DynamicList Size: "+dynamicList.size());
    	for(int i=0; i<size; i++){
    		de = dynamicList.get(i);
    		start = de.getStartTime().getTime();
    		end = de.getEndTime().getTime();
    		date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    		System.out.println("FINAL LIST The start date is: "+ date.format(start) + " " + dynamicList.get(i).getName() + dynamicList.get(i).getId());
    		System.out.println("FINAL LIST The end date is: "+ date.format(end)+" "+dynamicList.get(i).getName()+dynamicList.get(i).getId());
    	}
    }
    
    
}