package com.cse110.apk404.myCalendar.eventListHandler;

public class Calendar{
	
	
	private int YEAR; //for example 2016
	private int MONTH; //1 to 12
	private int DAY; // 1 to 31
	private int HOUR_OF_DAY; //0 to 23
	private int MINUTE; //0 to 59
	private int DAY_OF_WEEK; //1 to 7
	private String monthString; //Jan Feb ...
	
	public Calendar(int YEAR, 
			int MONTH, int DAY, int HOUR_OF_DAY, int MINUTE, int DAY_OF_WEEK, String monthString) throws CalendarError {
			
			setYear(YEAR);
			setMonth(MONTH);
			setDay(DAY);
			setHour(HOUR_OF_DAY);
			setMinute(MINUTE);
			setDayofweek(DAY_OF_WEEK);
	}
	
	
	
	public int getDayofweek() {
		return DAY_OF_WEEK;
	}
	
	public void setDayofweek(int DAY_OF_WEEK) {
		this.DAY_OF_WEEK = DAY_OF_WEEK;
	}
	
	public int getMinute() {
		return MINUTE;
	}
	
	public void setMinute(int MINUTE) {
		this.MINUTE = MINUTE;
	}
	
	public int getHour() {
		return HOUR_OF_DAY;
	}
	
	public void setHour(int HOUR_OF_DAY) {
		this.HOUR_OF_DAY = HOUR_OF_DAY;
	}
	
	public int getYear() {
		return YEAR;
	}
	
	public void setYear(int YEAR) {
		this.YEAR = YEAR;
	}
	
	public int getMonth() {
		return MONTH;
	}
	
	public void setMonth(int MONTH) {
		this.MONTH = MONTH;
	}
	
	public int getDay() {
		return DAY;
	}
	
	public void setDay(int DAY) {
		this.DAY = DAY;
	}
	
	public String convert() {
		int month = this.getMonth();
		switch (month) {
        case 1:  monthString = "Jan";
                 break;
        case 2:  monthString = "Feb";
                 break;
        case 3:  monthString = "Mar";
                 break;
        case 4:  monthString = "Apr";
                 break;
        case 5:  monthString = "May";
                 break;
        case 6:  monthString = "Jun";
                 break;
        case 7:  monthString = "Jul";
                 break;
        case 8:  monthString = "Aug";
                 break;
        case 9:  monthString = "Sep";
                 break;
        case 10: monthString = "Oct";
                 break;
        case 11: monthString = "Nov";
                 break;
        case 12: monthString = "Dec";
                 break;
        default: monthString = "Invalid month";
                 break;
    }
		return monthString;
	}
	
	public String DateKey(){
		int DAY = this.getDay();
		String monthString = this.convert();
		int YEAR = this.getYear();
		return DAY + " " + monthString + " " + YEAR;
	}
	
}
