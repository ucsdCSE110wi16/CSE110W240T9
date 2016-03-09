package com.cse110.apk404.myCalendar.eventListHandler;


import java.io.IOException;
import java.util.AbstractCollection;

public class CalendarDB {

	static CalendarObjectListInputStream in = null;
	static CalendarObjectListOutputStream out = null;
	static String[] filenames = null;
	
	public static void initDB(String[] filenames) throws IOException {
		CalendarDB.filenames = filenames;
		for (int i = 0; i < filenames.length; i++)
			CalendarDB.initList(i);
	}
	
	public static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> retriveList(int listID) throws IOException, CalendarError {
		in = new CalendarObjectListInputStream(filenames[listID]);
		CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> result = in.readList();
		in.close();
		return result;
		
	}
	
	public static void updateList(int listID, CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> obj) throws IOException {

		out = new CalendarObjectListOutputStream(filenames[listID]);
		out.writeList(obj);	
	}
	
	static boolean syncDB(Object something) {
		// this method is for SQL database
		return true;
	}
	
	static void initList(int listID) throws IOException {
		CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> obj = null;	
		switch (listID) {
			case 0: 
			case 1:
			case 2:
			case 3:/// list
		}
		
		out = new CalendarObjectListOutputStream(filenames[listID]);
		out.writeList(obj);
		out.close();
	}
}
