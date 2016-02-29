
public interface CalendarEvent{

	public abstract String getName();
	public abstract void setName(String name);
	public abstract Calendar getStartTime();
	public abstract void setStartTime(Calendar startTime);
	public abstract Calendar getEndTime();
	public abstract void setEndTime(Calendar endTime);
	public abstract boolean isStatic();
	public abstract void setStatic(boolean isStatic);
	public abstract String getDescription();
	public abstract void setDescription(String description);
	public abstract boolean isFinished();
	public abstract void setFinished(boolean isFinished);
	
}


