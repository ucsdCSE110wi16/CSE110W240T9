
public class Slot {
	
	private Time startTime;
	private Time endTime;
	private int length;
	
	public Slot(int year, int month, int weekday, int date, int start, int end) throws CalendarError {
		
		this.setStartTime(new Time(year, month, date, weekday, start, end));
		setEnd(end);
		
		if (end - start <= 0)
			throw new CalendarError("Invalid Time Slot");
		
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	
}
