import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class EventListHandler{

	private ObjectInputStream objIn; 
	private StaticEventList staticList;
	private DynamicEventList dynamicList;

	public void EventListInputStream(String filename) throws IOException {
		FileInputStream fi = new FileInputStream(filename);
		this.objIn = new ObjectInputStream(fi);     
	}
	
	public StaticEventList readStaticEventList() {
		StaticEventList staticList = null; 
		try {
			staticList = (StaticEventList) this.objIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	    return staticList;
	}

	
	
	public DynamicEventList readDynamicEventList() {
		DynamicEventList dynamicList = null; 
		try {
			this.setDynamicList((DynamicEventList) this.objIn.readObject());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	    return dynamicList;
	}

	public StaticEventList getStaticList() {
		return staticList;
	}

	public void setStaticList(StaticEventList staticList) {
		this.staticList = staticList;
	}

	public DynamicEventList getDynamicList() {
		return dynamicList;
	}

	public void setDynamicList(DynamicEventList dynamicList) {
		this.dynamicList = dynamicList;
	}
	public boolean createStaticEvent(String name, Time time, boolean isStatic, boolean isPeriodic, boolean isFinished, String comment) throws CalendarError{
		Slot slot = new Slot();
		StaticEvent staticEvent = new StaticEvent(name, slot, isStatic, isPeriodic, isFinished, comment);
	}
}
