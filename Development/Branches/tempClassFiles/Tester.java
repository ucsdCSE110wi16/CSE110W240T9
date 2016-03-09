package com.zhiweijia.nice;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;

public class Tester {

	public static ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> test(Context context)  throws CalendarError, IOException {

        CalendarDate startTime = new CalendarDate(2016, 1, 31, 22, 59, 6, "Jan");
		CalendarDate endTime = new CalendarDate(2016, 1, 31, 23, 59, 6, "Jan");
		CalendarDate startTime2 = new CalendarDate(2016, 1, 31, 21, 59, 6, "Jan");
		CalendarDate endTime2 = new CalendarDate(2016, 1, 31, 23, 59, 6, "Jan");
		EventListHandler handler = new EventListHandler();
		handler.initStaticList();
		boolean check = handler.createStaticEvent("testname", "basement", startTime, endTime,
				true, false, false, "scarlet", "red");
		handler.createStaticEvent("test", "basementsd", startTime2, endTime2,
				true, false, false, "scar", "blue");
		if(check) {
            Log.d("Tester", "success");
            System.out.println("success\n");
        }
		
		// handler.dynamicSort();

		StaticEventList staticEventList = handler.getStaticList();
		ArrayList<StaticEvent> staticArrayList = staticEventList.getList();
		StaticEvent staticEvent;
		for (int i=0; i<staticArrayList.size(); i++){
			System.out.println("the " + i + "th object:");
            Log.d("Tester", "the " + i + "th object:");
            staticEvent = staticArrayList.get(i);
			System.out.println(staticEvent.getName()+" "+staticEvent.getColor()+" "+staticEvent.getDateKey());
            Log.d("Tester", staticEvent.getName()+" "+staticEvent.getColor()+" "+staticEvent.getDateKey());
		}
		System.out.println();
		
		CalendarObjectListOutputStream out = new CalendarObjectListOutputStream("user_data", context);
		System.out.println(out.writeList(staticEventList));
		out.close();
		
		CalendarObjectListInputStream in = new CalendarObjectListInputStream("user_data", context);
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
            Log.d("Tester", "the " + i + "th object:");
            staticEvent1 = staticArrayList1.get(i);
			System.out.println(staticEvent1.getName()+" "+staticEvent1.getColor()+" "+staticEvent1.getDateKey());
            Log.d("Tester", staticEvent1.getName() + " " + staticEvent1.getColor() + " " + staticEvent1.getDateKey());
        }
		System.out.println();
		
		handler.createStaticEvent("shit", "at shitty", startTime, endTime, true, false, false, "meow", "white");
		StaticEventList a = handler.getStaticList();
		in.close();
		out = new CalendarObjectListOutputStream("user_data", context);
		out.writeList(a);
		out.close();
		
		in = new CalendarObjectListInputStream("user_data", context);
		
		
		
		StaticEventList t2 = null;
		try {
			t2 = (StaticEventList) in.readList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LENGTH: "+t2.getList().size());
        Log.d("Tester", "LENGTH: "+t2.getList().size());


        System.out.println("result of t2: ");
		ArrayList<StaticEvent> b = t2.getList();
		StaticEvent c;
		for (int i=0; i<b.size(); i++){
			System.out.println("the " + i + "th object:");
            Log.d("Tester", "the " + i + "th object:");
			c = b.get(i);
			System.out.println(c.getName()+" "+c.getColor()+" "+c.getDateKey());
            Log.d("Tester", c.getName()+" "+c.getColor()+" "+c.getDateKey());
		}
		System.out.println();
		
		//handler.removeEventById(temp);
		//System.out.println(staticArrayList.size());  */
		ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> result = new ArrayList<>();
		result.add(t2);
		return result;
	}

}
