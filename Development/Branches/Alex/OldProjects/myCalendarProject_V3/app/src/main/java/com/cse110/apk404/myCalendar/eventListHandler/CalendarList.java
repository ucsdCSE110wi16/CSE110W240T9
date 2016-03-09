package com.cse110.apk404.myCalendar.eventListHandler;
import java.util.PriorityQueue;


public class CalendarList{

	private PriorityQueue<Calendar> list;

	public CalendarList(){
		this.list = new PriorityQueue<Calendar>();
	}

	public PriorityQueue<Calendar> getList() {
		return list;
	}

	public void setList(PriorityQueue<Calendar> list) {
		this.list = list;
	}
	public Calendar getCalendar(){
		return list.poll();
	}

	public void addFreeTime(Calendar calendar) throws CalendarError {
		if (calendar == null)
			throw new CalendarError("Null Free Time");

		this.list.add(calendar);
	}
	public void removeFreeTime(Calendar calendar) throws CalendarError{
		if(calendar == null) throw new CalendarError("invalid slot");
		
		list.remove(calendar);
	}

}

