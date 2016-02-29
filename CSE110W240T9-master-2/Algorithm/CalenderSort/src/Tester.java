import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Tester {

	public static void main(String[] args)  throws CalendarError {
		int temp = 0;
		Calendar startTime = new Calendar(2016, 1, 31, 22, 59, 6, "Jan");
		Calendar endTime = new Calendar(2016, 1, 31, 23, 59, 6, "Jan");
		Calendar startTime2 = new Calendar(2016, 1, 31, 21, 59, 6, "Jan");
		Calendar endTime2 = new Calendar(2016, 1, 31, 23, 59, 6, "Jan");
		System.out.println(startTime.DateKey());
		EventListHandler handler = new EventListHandler();
		handler.initStaticList();
		boolean check = handler.createStaticEvent("testname", "basement", startTime, endTime,
				true, false, false, "scarlet", "red");
		handler.createStaticEvent("test", "basementsd", startTime2, endTime2,
				true, false, false, "scarletsb", "red");
		if(check)
		//System.out.println("success");
		
		handler.dynamicSort();
		
		StaticEventList staticEventList = handler.getStaticList();
		ArrayList<StaticEvent> staticArrayList = staticEventList.getList();
		StaticEvent staticEvent;
		for (int i=0; i<staticArrayList.size(); i++){
			staticEvent = staticArrayList.get(i);
			temp = staticEvent.getId();
			System.out.println(staticEvent.getId());
		}
		
		handler.removeEventById(temp);
		System.out.println(staticArrayList.size());
	}

}
