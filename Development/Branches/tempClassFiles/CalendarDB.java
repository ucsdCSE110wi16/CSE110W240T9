package com.zhiweijia.nice;

import android.content.Context;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;

public class CalendarDB {

	static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list1 = null;
	static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list2 = null;
	static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list3 = null;

	static CalendarObjectListInputStream in = null;
	static CalendarObjectListOutputStream out = null;
	static CalendarSQLiteDB sqlitedb = null;
	static String[] filenames = null;
	static Context context = null;
	
	public static void initDBLocal(String[] filenames, Context context) throws IOException {
		CalendarDB.filenames = filenames;
		CalendarDB.context = context;
		CalendarDB.initListLocal(1);
		CalendarDB.initListLocal(2);
		CalendarDB.initListLocal(3);
	}
	
	public static CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> retriveListLocal(int listID) throws IOException, CalendarError {
		in = new CalendarObjectListInputStream(filenames[listID], context);
		CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> result = in.readList();
		in.close();
		return result;
		
	}
	
	public static void updateListLocal(int listID, CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> obj) throws IOException {

		out = new CalendarObjectListOutputStream(filenames[listID], context);
		out.writeList(obj);	
	}

	public static void initSQLiteDB(String[] filenames, Context context) throws IOException {
		sqlitedb = new CalendarSQLiteDB(context);
	}

	public CalendarUser createUser(String email, String password,
								   CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> obj1,
								   CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> obj2,
								   CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> obj3) {
		ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> list = new ArrayList<>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		return new CalendarUser(email, password, list);
	}

	public CalendarUser getUserByEmail(String email) {
		return sqlitedb.getUserByEmail(email);
	}

	public void updateUserData(CalendarUser user) { sqlitedb.updateUserData(user); }

	public ArrayList<String> getAllUserEmail() { return sqlitedb.getAllUserEmail(); }

	public void addUser(CalendarUser user) {
		sqlitedb.addUser(user);
	}

	static void initListLocal(int listID) throws IOException {
		CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> obj = null;	
		switch (listID) {
			case 0: 
			case 1:
			case 2:
			case 3:/// list
		}
		
		out = new CalendarObjectListOutputStream(filenames[listID], context);
		out.writeList(obj);
		out.close();
	}
}
