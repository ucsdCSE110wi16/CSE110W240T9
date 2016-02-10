
public class Slot {
	
	private Time startTime;
	private int end;  // 0 to 47 
	private boolean nextDay; // whether the end time is 0 o'clock
	private int length;
	
	public Slot(int year, int month, int date, int day, int start, int end, boolean nextDay) throws CalendarError {
		
		this.setStartTime(new Time(year, month, date, day, start)); 
		setEnd(end);
		setNextDay(nextDay);
		
		if (end - start <= 0 && !(nextDay))
			throw new CalendarError("Invalid Time Slot");
		
		if (nextDay)
			setLength(48 - start);
		else
			setLength(end - start);
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	
}
