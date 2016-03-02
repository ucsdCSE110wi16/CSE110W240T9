//package com.cse110.apk404.myCalendar.eventListHandler;

import java.util.ArrayList;

public interface CalendarEventList {
	public ArrayList<StaticEvent> getList();
	public void setList(ArrayList<StaticEvent> list);
	public boolean addEvent(StaticEvent event) throws CalendarError;
}