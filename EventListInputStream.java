import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class EventListInputStream {

	ObjectInputStream objIn; 
	
	public EventListInputStream(String filename) throws IOException {
		FileInputStream fi = new FileInputStream(filename);
		this.objIn = new ObjectInputStream(fi);     
	}
	
	public CalendarEventList readList() {
		CalendarEventList result = null; 
		try {
			result = (CalendarEventList) this.objIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	    return result;
	}
	
	public void close() throws IOException {
		this.objIn.close();
	}
}
