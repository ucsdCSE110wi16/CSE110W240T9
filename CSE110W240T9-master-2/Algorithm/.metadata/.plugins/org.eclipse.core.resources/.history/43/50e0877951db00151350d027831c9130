
public class DynamicEvent{

	private String Id;
	private Calendar deadline;
	private int estimatedLength;
	private String name;
	private boolean isStatic;
	private boolean isFinished;
	private String description = "";
	
	public DynamicEvent(String name, int estimatedLength, boolean isStatic, Calendar deadline, boolean isFinished, String description) throws CalendarError{
		setName(name);
		setStatic(isStatic);
		setFinished(isFinished);
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

	public String getDescrption() {
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
	
}
