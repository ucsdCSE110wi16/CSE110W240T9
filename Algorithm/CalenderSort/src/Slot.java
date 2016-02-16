
public class Slot {
	
	private Time startTime;//start and end must be in the same day
	private Time endTime;
	private int length;//in format of minutes
	
	public Slot(Time startTime, Time endTime) throws CalendarError {
		
		if (startTime.getYear() != endTime.getYear() || startTime.getMonth() != endTime.getMonth() || 
				startTime.getDay() != endTime.getDay() ||
				startTime.getHour() - endTime.getHour() <= 0)
			throw new CalendarError("Invalid Time Slot");
		
		setLength((endTime.getHour() - startTime.getHour())*60 + endTime.getMinute() - startTime.getMinute());
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
