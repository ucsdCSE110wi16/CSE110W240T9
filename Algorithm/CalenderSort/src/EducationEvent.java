
public class EducationEvent extends CalendarEvent {

	private static final long serialVersionUID = 1L;

	private String room;
	
	public EducationEvent(String name) throws CalendarError {
		
		super(name);
		
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}
