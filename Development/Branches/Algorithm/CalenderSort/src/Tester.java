//package com.cse110.apk404.myCalendar.eventListHandler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.util.Date;


public class Tester {

	public static void main(String[] args)  throws CalendarError, IOException {




		EventListHandler.setStartTimeOfDay(9);
		EventListHandler.setEndTimeOfDay(21);
		//int temp = 0;
		Calendar startTime = Calendar.getInstance();
		startTime.set(2016, Calendar.MONTH, 8, 17, 00);
		//		startTime.set(2016,1,29,17,01);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2016, Calendar.MONTH, 8, 18, 00);
		Date startDate = endTime.getTime();


		//		System.out.println((endTime.getTime().getTime() - startTime.getTime().getTime()) / (1000 * 60 * 60 * 24));
		Calendar startTime2 = Calendar.getInstance();
		startTime2.set(2016, Calendar.MONTH, 7, 8, 00);
		Calendar endTime2 = Calendar.getInstance();
		endTime2.set(2016, Calendar.MONTH, 7, 20, 00);

		Calendar startTime3 = Calendar.getInstance();
		startTime3.set(2016, Calendar.MONTH,7, 15, 00);
		Calendar endTime3 = Calendar.getInstance();
		endTime3.set(2016, Calendar.MONTH, 7, 17, 00);

		//test for getting hour
		//System.out.println("Time test: " + (endTime2.get(Calendar.HOUR_OF_DAY) - startTime2.get(Calendar.HOUR_OF_DAY)));


		//DateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//Date date = endTime2.getTime();
		//System.out.println(time.format(startDate));
		//System.out.println(time.format(date));

		EventListHandler.initStaticList();
//		boolean check = EventListHandler.createStaticEvent("do homework", "basement", startTime, endTime,
//				true, false, false, "scarlet", "red");
//		if(check)
//			System.out.println("1success\n");
//
//		check = EventListHandler.createStaticEvent("sleep","basement sd", startTime2, endTime2,
//				true, false, false, "scar", "blue");
//
//		if(check)
//			System.out.println("2success\n");
//
//		check = EventListHandler.createStaticEvent("sleepx","basement sdx", startTime3, endTime3,
//				true, false, false, "scar", "blue");
//
//		if(check)
//			System.out.println("3success\n");


		Calendar dyndeadline = Calendar.getInstance();
		dyndeadline.set(2016, Calendar.MONTH, 10, 20, 0);

		Calendar dyndeadline2 = Calendar.getInstance();
		dyndeadline2.set(2016, Calendar.MONTH, 10, 20, 0);
		
		StaticEventList staticEventList = EventListHandler.getStaticList();
		ArrayList<StaticEvent> staticArrayList = staticEventList.getList();
		StaticEvent staticEvent;
		for (int i=0; i<staticArrayList.size(); i++){
			System.out.println("The " + i + "th event:");
			staticEvent = staticArrayList.get(i);
			System.out.println(staticEvent.getName()+" "+staticEvent.getColor()+" "+ staticEvent.getId());
		}

		EventListHandler.initDynamicList();

		EventListHandler.initDeadlineList();

		EventListHandler.createDynamicEvent("scarlet", false, "CSEB", "bgwp", "red", dyndeadline, 360, false);
		
		//EventListHandler.createDynamicEvent("steven", false, "CSEA", "ggwp", "red", dyndeadline2, 240, false);
		
		DynamicEventList dynamicList = EventListHandler.getDynamicList();
		dynamicList.print();
		


		/*
		CalendarObjectListOutputStream out = new CalendarObjectListOutputStream("/Desktop/Data");
		System.out.println(out.writeList(staticEventList));
		out.close();

		CalendarObjectListInputStream in = new CalendarObjectListInputStream("/Desktop/Data");
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
			System.out.println(staticEvent1.getName()+" "+staticEvent1.getColor() + " " 
					+ staticEvent1.getId());
		}
		System.out.println();

		handler.createStaticEvent("shit", "at shitty", startTime, endTime, true, false, false, "meow", "white");
		StaticEventList a = handler.getStaticList();
		in.close();
		out = new CalendarObjectListOutputStream("$HOME/Desktop/Data");
		out.writeList(a);
		out.close();

		in = new CalendarObjectListInputStream("$HOME/Desktop/Data");



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
			System.out.println(c.getName()+" "+c.getColor());
		}
		System.out.println();*/

		//handler.removeEventById(temp);
		//System.out.println(staticArrayList.size());  */
	}

}
