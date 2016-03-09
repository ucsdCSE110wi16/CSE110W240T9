package com.cse110.apk404.myCalendar.eventListHandler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Tester {

	public static void main(String[] args)  throws CalendarError, IOException {
		int temp = 0;
		// CalendarDate(int YEAR, int MONTH, int DAY, int HOUR_OF_DAY, int MINUTE, int DAY_OF_WEEK, String monthString)
		CalendarDate startTime = new CalendarDate(2016, 2, 31, 10, 0, 0, "Feb");
		CalendarDate endTime = new CalendarDate(2016, 2, 31, 12, 0, 0, "Feb");
		CalendarDate startTime2 = new CalendarDate(2016, 2, 31, 14, 0, 0, "Feb");
		CalendarDate endTime2 = new CalendarDate(2016, 2, 31, 15, 0, 0, "Feb");
		EventListHandler handler = new EventListHandler();
		handler.initStaticList();
		boolean check = handler.createStaticEvent("Event Name", "Event Location", startTime, endTime,
				true, false, false, "Description 1", "red");
		handler.createStaticEvent("test", "basementsd", startTime2, endTime2,
				true, false, false, "Description 2", "blue");
		if(check)
		    System.out.println("success\n");
		
		// handler.dynamicSort();
		
		StaticEventList staticEventList = handler.getStaticList();
		ArrayList<StaticEvent> staticArrayList = staticEventList.getList();
		StaticEvent staticEvent;
		for (int i=0; i<staticArrayList.size(); i++){
			System.out.println("the " + i + "th object:");
			staticEvent = staticArrayList.get(i);
			System.out.println(staticEvent.getName()+" "+staticEvent.getColor()+" "+staticEvent.getDateKey());
		}
		System.out.println();
		
		CalendarObjectListOutputStream out = new CalendarObjectListOutputStream("/home/zhiwei/Documents/Java/CalendarProject/Calendar/src/data");
		System.out.println(out.writeList(staticEventList));
		out.close();
		
		CalendarObjectListInputStream in = new CalendarObjectListInputStream("/home/zhiwei/Documents/Java/CalendarProject/Calendar/src/data");
		StaticEventList t1 = null;
		try {
			t1 = (StaticEventList) in.readList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		System.out.println("result of t1: ");
		ArrayList<StaticEvent> staticArrayList1 = t1.getList();
		StaticEvent staticEvent1;
		for (int i=0; i<staticArrayList1.size(); i++){
			System.out.println("the " + i + "th object:");
			staticEvent1 = staticArrayList1.get(i);
			System.out.println(staticEvent1.getName()+" "+staticEvent1.getColor()+" "+staticEvent1.getDateKey());
		}
		System.out.println();
		
		handler.createStaticEvent("shit", "at shitty", startTime, endTime, true, false, false, "meow", "white");
		StaticEventList a = handler.getStaticList();
		in.close();
		out = new CalendarObjectListOutputStream("/home/zhiwei/Documents/Java/CalendarProject/Calendar/src/data");
		out.writeList(a);
		out.close();
		
		in = new CalendarObjectListInputStream("/home/zhiwei/Documents/Java/CalendarProject/Calendar/src/data");
		
		
		
		StaticEventList t2 = null;
		try {
			t2 = (StaticEventList) in.readList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LENGTH: "+t2.getList().size());

		System.out.println("result of t2: ");
		ArrayList<StaticEvent> b = t2.getList();
		StaticEvent c;
		for (int i=0; i<b.size(); i++){
			System.out.println("the " + i + "th object:");
			c = b.get(i);
			System.out.println(c.getName()+" "+c.getColor()+" "+c.getDateKey());
		}
		System.out.println();
		
		//handler.removeEventById(temp);
		//System.out.println(staticArrayList.size());  */
	}

}
