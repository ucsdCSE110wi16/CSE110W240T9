
public class Slot {
	
	private int year;
	private int month;
	private int date;
	private int day; // 0 to 6
	private int start;  // 0 to 47
	private int end; 
	private boolean nextDay;
	private int length;
	
	public Slot(int year, int month, int date, int day, int start, int end, boolean nextDay) throws CalendarError {
		
		setYear(year);
		setMonth(month);
		setDate(date);
		setDay(day);
		setStart(start);
		setEnd(end);
		setNextDay(nextDay);
		
		if (end - start <= 0 && !(nextDay))
			throw new CalendarError("Invalid Time Slot");
		
		if (nextDay)
			setLength(48 - start);
		else
			setLength(end - start);
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
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}


	public boolean isNextDay() {
		return nextDay;
	}


	public void setNextDay(boolean nextDay) {
		this.nextDay = nextDay;
	}
	
	
}
