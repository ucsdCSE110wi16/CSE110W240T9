package com.zhiweijia.nice;

import java.util.ArrayList;

public class StaticEventList implements CalendarObjectList<ArrayList<StaticEvent>, StaticEvent> {


	private static final long serialVersionUID = 1L;
	
	private ArrayList<StaticEvent> staticList; //list of all static events

	public StaticEventList() {
		this.staticList = new ArrayList<StaticEvent>();
	}
	
	public ArrayList<StaticEvent> getList() {
		return staticList;
	}
	

	public void setList(ArrayList<StaticEvent> list) {
		this.staticList = list;
	}

	public boolean addEvent(StaticEvent event) throws CalendarError {
		boolean check = true;
		if (event == null){
			check = false;
			throw new CalendarError("Null Event");
		}

		this.staticList.add(event);
		return check;
	}

    
    public boolean removeEventById(int temp) throws CalendarError {
    	boolean check = false;
		if (temp == 0)
			throw new CalendarError("Null Event");
		StaticEvent eventToRemove;
		for (int i = 0; i< staticList.size(); i++){
			eventToRemove = staticList.get(i);
			  if (eventToRemove.getId() == temp){
			    staticList.remove(eventToRemove);
			    check = true;
			  }
		}
		return check;
	}

}