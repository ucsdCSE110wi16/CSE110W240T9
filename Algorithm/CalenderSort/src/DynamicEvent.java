
public class DynamicEvent extends CalendarEvent implements StaticEvent {

	private static final long serialVersionUID = 1L;

	private Time deadline;
	private int estimatedLength;
	
	public DynamicEvent(String name, Time deadline) throws CalendarError {
		super(name);
		setDeadline(deadline);
		setEstimatedLength(estimatedLength);
	}

	@Override
	public Time getDeadline() {	
		return deadline;
	}

	@Override
	public int getEstimatedLength() {
		return estimatedLength;
	}

	@Override
	public void setDeadline(Time deadline) {
		this.deadline = deadline;
	}

	@Override
	public void setEstimatedLength(int estimatedLength) {
		this.estimatedLength = estimatedLength;
	}
}
