
<<<<<<< HEAD

public class DynamicEvent implements CalendarEvent{

	private String Id; //Id is in the format of DateKey + Name + startTime such as 18022016CSE1102359
	private String dateKey; //dateKey is the date of the event 12 Feb 2016
	private String name;  //name of the event
	private Calendar startTime; //startTime as a Calendar object
	private Calendar endTime; //endTime as a Calendar object
	private boolean isStatic; //if the event is static
	private boolean isFinished; //if the event is finished
	private String location = ""; //location of event
	private String description = ""; //description of event
	private String color = "";
	private Calendar deadline;
	private int estimatedLength; // estimated time to complete

=======
public class DynamicEvent implements CalendarEvent{

	private String Id;
	private Time deadline;
	private int estimatedLength;
	private String name;
	private Slot slot;
	private boolean isStatic;
	private boolean isFinished;
	private String comment = "";
>>>>>>> dfa1fce35586f406121b47a9e23909eb089dfbee
	
	public DynamicEvent(String Id, String dateKey, String name, boolean isStatic, String location, String description, String color,
			Calendar deadline) throws CalendarError{
		setId(Id);
		setDateKey(dateKey);
		setName(name);
		setStatic(isStatic);
		setStartTime(null);
		setEndTime(null);
		setLocation(location);
		setColor(color);
		setFinished(false);
		setDeadline(deadline);
		setDescription(description);
		setEstimatedLength(estimatedLength);
	}

	public Calendar getDeadline() {	
		return deadline;
	}


	public int getEstimatedLength() {
		return estimatedLength;
	}

	public void setDeadline(Calendar deadline) {
		this.deadline = deadline;
	}

	public void setEstimatedLength(int estimatedLength) {
		this.estimatedLength = estimatedLength;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean isStatic() {
		return this.isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinished() {
		return this.isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}
<<<<<<< HEAD

	public String getDateKey() {
		return dateKey;
	}

	public void setDateKey(String dateKey) {
		this.dateKey = dateKey;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
=======
>>>>>>> dfa1fce35586f406121b47a9e23909eb089dfbee
}
