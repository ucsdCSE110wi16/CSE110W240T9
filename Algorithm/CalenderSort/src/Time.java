public class Time {

	private int year;
	private int month; //1 to 12
	private int day; // 1 to 31
	private int hour; //0 to 23
	private int minute; //0 to 59
	private int dayofweek; //1 to 7
	
	public Time(int year, int month, int day, int hour,int minute, int dayofweek) throws CalendarError {
	
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setDayofweek(dayofweek);
	}
	
	
	public int getDayofweek() {
		return dayofweek;
	}
	
	public void setDayofweek(int dayofweek) {
		this.dayofweek = dayofweek;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public int getHour() {
		return hour;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public String StringKey(){
		return year + "" + month + "" + day + "" + hour + "" + minute + "" + dayofweek;
	}
	
}

