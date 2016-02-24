//
//public class DynamicEvent implements CalendarEvent{
//
//	private String Id;
//	private Calendar deadline;
//	private int estimatedLength;
//	private String name;
//	private Calendar startTime;
//	private Calendar endTime;
//	private boolean isStatic;
//	private boolean isFinished;
//	private String comment = "";
//	
//	public DynamicEvent(String name, int estimatedLength, boolean isStatic, Calendar deadline, boolean isFinished, String comment) throws CalendarError{
//		setName(name);
//		setStatic(isStatic);
//		setFinished(isFinished);
//		setDeadline(deadline);
//		setComment(comment);
//		setEstimatedLength(estimatedLength);
//	}
//
//	public Calendar getDeadline() {	
//		return deadline;
//	}
//
//
//	public int getEstimatedLength() {
//		return estimatedLength;
//	}
//
//	public void setDeadline(Calendar deadline) {
//		this.deadline = deadline;
//	}
//
//	public void setEstimatedLength(int estimatedLength) {
//		this.estimatedLength = estimatedLength;
//	}
//
//	@Override
//	public String getName() {
//		return this.name;
//	}
//
//	@Override
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//	@Override
//	public boolean isStatic() {
//		return this.isStatic;
//	}
//
//	@Override
//	public void setStatic(boolean isStatic) {
//		this.isStatic = isStatic;
//	}
//
//	@Override
//	public String getComment() {
//		return this.comment;
//	}
//
//	@Override
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	@Override
//	public boolean isFinished() {
//		return this.isFinished;
//	}
//
//	@Override
//	public void setFinished(boolean isFinished) {
//		this.isFinished = isFinished;
//	}
//
//	public String getId() {
//		return Id;
//	}
//
//	public void setId(String id) {
//		this.Id = id;
//	}
//
//
//
//
//	
//}
