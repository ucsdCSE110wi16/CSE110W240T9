
public class Time {

	private int year;
	private int month;
	private int date;
	private int day; // 0 to 6
	private int time;  // 0 to 47
	
	public Time(int year, int month, int date, int day, int time) throws CalendarError {
		
		setYear(year);
		setMonth(month);
		setDate(date);
		setDay(day);
		setTime(time);
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
	
	public int getDate() {
		return date;
	}
	
	public void setDate(int date) {
		this.date = date;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
}

