package com.zhiweijia.nice;

import java.util.PriorityQueue;

public class CalendarDateList implements CalendarObjectList<PriorityQueue<CalendarDate>, CalendarDate> {

	private static final long serialVersionUID = 1L;
	
	private PriorityQueue<CalendarDate> list;

	public CalendarDateList(){
		this.list = new PriorityQueue<CalendarDate>();
	}

	@Override
	public PriorityQueue<CalendarDate> getList() {
		return list;
	}
	
	public CalendarDate getCalendarDate(){
		return list.poll();
	}

	public void addFreeTime(CalendarDate calendarDate) throws CalendarError {
		if (calendarDate == null)
			throw new CalendarError("Null Free Time");

		this.list.add(calendarDate);
	}
	
	public void removeFreeTime(CalendarDate calendarDate) throws CalendarError{
		if (calendarDate == null) 
			throw new CalendarError("invalid slot");
		
		list.remove(calendarDate);
	}

	@Override
	public boolean addEvent(CalendarDate obj) throws CalendarError {
		addFreeTime(obj);
		return true;
	}

	@Override
	public void setList(PriorityQueue<CalendarDate> list) throws CalendarError {		
		this.list = list;	
	}

}

