package com.cse110.apk404.myCalendar.eventListHandler;
import com.cse110.apk404.myCalendar.eventListHandler.Calendar;
import java.util.PriorityQueue;

public class CalendarProperties {
	
	private int length;//in format of minutes
	
	public int length(Calendar startTime, Calendar endTime){
		return length = (endTime.getHour() - startTime.getHour())*60 + 
				endTime.getMinute() - startTime.getMinute();
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

}
