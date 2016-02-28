package com.zhiweijia.nice;

import java.io.Serializable;

public class CalendarProperties implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int length; //in format of minutes
	
	public int length(CalendarDate startTime, CalendarDate endTime){
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
