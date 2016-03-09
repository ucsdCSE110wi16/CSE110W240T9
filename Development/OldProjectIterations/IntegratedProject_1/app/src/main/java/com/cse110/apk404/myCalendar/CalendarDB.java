package com.cse110.apk404.myCalendar;

import android.content.Context;
import android.util.Log;

import com.cse110.apk404.myCalendar.apiclient.Event;

import java.io.IOException;
import java.sql.Connection;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class CalendarDB {

	static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list0 = null;
	static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list1 = null;
	static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list2 = null;
	static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list3 = null;

	static CalendarObjectListInputStream in = null;
	static CalendarObjectListOutputStream out = null;
	static final String[] filenames = new String[] {"staticData", "dynamicData", "deadlineData", "finishedData"};

	public static void initDBLocal(Context context) throws IOException {
		CalendarDB.initListLocal(0, context);
		CalendarDB.initListLocal(1, context);
		CalendarDB.initListLocal(2, context);
		CalendarDB.initListLocal(3, context);
	}
	
	public static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> retriveListLocal(int listID, Context context) throws IOException, CalendarError {
		in = new CalendarObjectListInputStream(filenames[listID], context);
		CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> result = in.readList();
		in.close();

		return result;
	}
	
	public static void updateListLocal(int listID, CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> obj, Context context) throws IOException {

		out = new CalendarObjectListOutputStream(filenames[listID], context);
		out.writeList(obj);	
	}

    public static void syncFromCloud(ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>>  list) {

		try {

			EventListHandler.setStaticList((StaticEventList) (list.get(0)));
			EventListHandler.setDynamicList((DynamicEventList) (list.get(1)));
			EventListHandler.setDeadlineList((DynamicEventList) (list.get(2)));
			EventListHandler.setFinishedDynamicList((DynamicEventList) (list.get(3)));

		} catch (Exception e) {
			Log.e("shit", e.toString());
		}
    }

	private static void initListLocal(int listID, Context context) throws IOException {
		CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list = null;

		switch (listID) {
			case 0: EventListHandler.initStaticList(); list = EventListHandler.getStaticList(); break;
			case 1: EventListHandler.initDynamicList(); list = EventListHandler.getDynamicList(); break;
			case 2: EventListHandler.initDynamicList(); list = EventListHandler.getDeadlineList(); break;
			case 3: EventListHandler.initDynamicList(); list = EventListHandler.getFinishedDynamicList(); break;
		}
		
		out = new CalendarObjectListOutputStream(filenames[listID], context);
		out.writeList(list);
		out.close();
	}
}
