import java.io.Serializable;

public class DynamicEvent implements CalendarEvent,Serializable{

	private static final long serialVersionUID = 1L;

	private Time deadline;
	private int estimatedLength;
	private String name;
	private Slot slot;
	private boolean isStatic;
	private boolean isFinished;
	private String comment = "";
	
	public DynamicEvent(String name, int estimatedLength, boolean isStatic, Time deadline, boolean isFinished, String comment) throws CalendarError{
		setName(name);
		setSlot(null);
		setStatic(isStatic);
		setFinished(isFinished);
		setDeadline(deadline);
		setComment(comment);
		setEstimatedLength(estimatedLength);
	}

	public Time getDeadline() {	
		return deadline;
	}


	public int getEstimatedLength() {
		return estimatedLength;
	}

	public void setDeadline(Time deadline) {
		this.deadline = deadline;
	}

	public void setEstimatedLength(int estimatedLength) {
		this.estimatedLength = estimatedLength;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Slot getSlot() {
		return this.slot;
	}

	@Override
	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	@Override
	public boolean isStatic() {
		return this.isStatic;
	}

	@Override
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public boolean isFinished() {
		return this.isFinished;
	}

	@Override
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
}
