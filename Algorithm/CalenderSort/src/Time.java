public class Time {

	private int year;
	private int month; //1 to 12
	private int day; // 1 to 31
	private int hour; //0 to 23
	private int minute; //0 to 59
	private int dayofweek; //1 to 7
	private String monthString;
	
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
	
	public String StringKey(){
		int day = this.getDay();
		String monthString = this.convert();
		int year = this.getYear();
		return day + " " + monthString + " " + year;
	}
	
}

