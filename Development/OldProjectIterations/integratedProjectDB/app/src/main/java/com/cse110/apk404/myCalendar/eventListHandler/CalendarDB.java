package com.cse110.apk404.myCalendar.eventListHandler;

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

	static CalendarObjectListInputStream in = null;
	static CalendarObjectListOutputStream out = null;
	static CalendarSQLiteDB sqlitedb = null;
	static final String[] filenames = new String[] {"staticData", "dynamicData", "deadlineData"};

	public static void initDBLocal(Context context) throws IOException {
		CalendarDB.initListLocal(0, context);
		CalendarDB.initListLocal(1, context);
		CalendarDB.initListLocal(2, context);
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

	public static Connection initDBOnline() {

        Connection con = SQLServerAdapter.getConnection();

        if (con == null) {
            Log.e("Database", "connection fails!");
        }

        SQLServerAdapter.createTableIfNotExist(con);

        return con;
	}

	public static CalendarUser createUser(String email, String password,
								   CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list0,
								   CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list1,
								   CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list2) {
		ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> list = new ArrayList<>();
		list.add(list0);
		list.add(list1);
		list.add(list2);
		return new CalendarUser(email, password, list);
	}


    public static void syncFromCloud(ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>>  list) {

        // TODO sync all three lists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        EventListHandler.setStaticList((StaticEventList) (list.get(0)));
        EventListHandler.setDynamicList((DynamicEventList) (list.get(1)));
        // EventListHandler.setDeadlineList((DeadlineList)(list.get(2)));

    }

	public static void updateUserOnline(String email, Connection con) {
        ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> data = new ArrayList<>();

        // TODO update all three lists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        data.add(EventListHandler.getStaticList());
        data.add(EventListHandler.getDynamicList());
        data.add(EventListHandler.getDynamicList());

        SQLServerAdapter.updateLists(email, con, data);
    }

	public static void addUser(CalendarUser user, Connection con) {
        SQLServerAdapter.addUser(user, con);
	}

	private static void initListLocal(int listID, Context context) throws IOException {
		CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list = null;

        // TODO cases for all three lists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		switch (listID) {
			case 0: EventListHandler.initStaticList(); list = EventListHandler.getStaticList(); break;
			//case 2: handler.initDynamicList(); list = handler.getDynamicList();
		}
		
		out = new CalendarObjectListOutputStream(filenames[listID], context);
		out.writeList(list);
		out.close();
	}
}
