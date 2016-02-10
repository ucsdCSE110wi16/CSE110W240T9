
public interface CalendarEvent{

	public abstract String getName();
	public abstract void setName(String name);
	public abstract Slot getSlot();
	public abstract void setSlot(Slot slot);
	public abstract boolean isStatic();
	public abstract void setStatic(boolean isStatic);
	public abstract boolean isPeriodic();
	public abstract void setPeriodic(boolean isPeriodic);
	public abstract String getComment();
	public abstract void setComment(String comment);
	public abstract boolean isFinished();
	public abstract void setFinished(boolean isFinished);
	
}


