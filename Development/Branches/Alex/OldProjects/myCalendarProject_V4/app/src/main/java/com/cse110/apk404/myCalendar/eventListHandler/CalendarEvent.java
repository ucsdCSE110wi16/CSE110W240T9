package com.cse110.apk404.myCalendar.eventListHandler;

public interface CalendarEvent extends CalendarObject {

	public abstract String getName();
	public abstract void setName(String name);
	public abstract CalendarDate getStartTime();
	public abstract void setStartTime(CalendarDate startTime);
	public abstract CalendarDate getEndTime();
	public abstract void setEndTime(CalendarDate endTime);
	public abstract boolean isStatic();
	public abstract void setStatic(boolean isStatic);
	public abstract String getDescription();
	public abstract void setDescription(String description);
	public abstract boolean isFinished();
	public abstract void setFinished(boolean isFinished);
	public abstract void setColor(String color);
	public abstract String getColor();
	
}


