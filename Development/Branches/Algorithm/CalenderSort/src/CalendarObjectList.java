//package com.cse110.apk404.myCalendar.eventListHandler;

import java.io.Serializable;
import java.util.AbstractCollection;

public interface CalendarObjectList<T extends AbstractCollection<? extends CalendarObject>, E extends CalendarObject> extends Serializable {
	
	public T getList();
	
	public void setList(T list) throws CalendarError;
	
	public boolean addEvent(E obj) throws CalendarError;

}