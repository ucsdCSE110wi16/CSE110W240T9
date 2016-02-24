
public class CalendarProperties {
	
	private int length;//in format of minutes
	
	public int length(Calendar startTime, Calendar endTime){
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
