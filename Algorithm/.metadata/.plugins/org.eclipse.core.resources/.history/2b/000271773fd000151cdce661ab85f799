import java.io.Serializable;

public class CalendarEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private Slot slot;
	private boolean isStatic;
	private boolean isPeriodic;
	private boolean isFinished;
	private String comment = "";
	
	public CalendarEvent(String name) throws CalendarError {
		
		if (name == "")
			throw new CalendarError("Invalid Event Name");
			
		this.setName(name);
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

}


