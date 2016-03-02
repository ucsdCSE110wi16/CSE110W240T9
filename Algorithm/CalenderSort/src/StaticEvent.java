//package com.cse110.apk404.myCalendar.eventListHandler;

import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StaticEvent implements CalendarEvent {

	private static final long serialVersionUID = 1L;
	
	private long Id; //Random Long Number
	//private String dateKey; //dateKey is the date of the event 12 Feb 2016
	private String name;  //name of the event

	private Calendar startTime; //startTime as a Calendar object
	private Calendar endTime; //endTime as a Calendar object
	private boolean isStatic; //if the event is static
	private boolean isPeriodic; //if the event is periodic
	private boolean isFinished; //if the event is finished
	private String location = ""; //location of event
	private String description = ""; //description of event
	private String color = "";

	
	public StaticEvent(String name, String location, Calendar startTime, Calendar endTime,
		boolean isStatic, boolean isPeriodic, boolean isFinished, String description, String color) throws CalendarError {

		if (name == "")
			throw new CalendarError("Invalid Event Name");
		if (startTime == null || endTime == null){
			
			throw new CalendarError("Invalid time");
		}
			

		this.setName(name);
		this.setStatic(isStatic);
		this.setDescription(description);
		this.setFinished(isFinished);
		this.setPeriodic(isPeriodic);
		this.setStartTime(startTime);
		this.setEndTime(endTime);

		this.setLocation(location);
		this.setColor(color);
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isPeriodic() {
		return isPeriodic;
	}

	public void setPeriodic(boolean isPeriodic) {
		this.isPeriodic = isPeriodic;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
/*
	public String getDateKey() {
		return dateKey;
	}

	public void setDateKey(String dateKey) {
		this.dateKey = dateKey;
	}
*/

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		this.Id = id;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	public Calendar getEndTime() {
		return endTime;
	}


	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}






}