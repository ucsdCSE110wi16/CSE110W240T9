
public class StaticEvent implements CalendarEvent{

	private String Id; //Id is in the format of DateKey + Name + startTime such as 18022016CSE1102359
	private String dateKey; //dateKey is the date of the event 12 Feb 2016
	private String name;  //name of the event
	private Slot slot; //slot class which handles the length of the event, etc.
	private boolean isStatic; //if the event is static
	private boolean isPeriodic; //if the event is periodic
	private boolean isFinished;
	private String comment = "";

	
public StaticEvent(String dateKey, String name, Slot slot, boolean isStatic, boolean isPeriodic, boolean isFinished, String comment) throws CalendarError {
		
		if (name == "")
			throw new CalendarError("Invalid Event Name");
		if (slot == null){
			throw new CalendarError("Invalid time");
		}
			
		this.setDateKey(dateKey);
		this.setName(name);
		this.setStatic(isStatic);
		this.setComment(comment);
		this.setFinished(isFinished);
		this.setPeriodic(isPeriodic);
		this.setSlot(slot);
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isPeriodic() {
		return isPeriodic;
	}

	public void setPeriodic(boolean isPeriodic) {
		this.isPeriodic = isPeriodic;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public String getDateKey() {
		return dateKey;
	}

	public void setDateKey(String dateKey) {
		this.dateKey = dateKey;
	}


	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}
}
