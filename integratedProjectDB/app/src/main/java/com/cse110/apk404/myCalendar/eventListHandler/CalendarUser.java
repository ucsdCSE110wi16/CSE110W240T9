package com.cse110.apk404.myCalendar.eventListHandler;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class CalendarUser {

	private String email = null;
	private String password = null;
	private ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> data;
	
	public CalendarUser(String email, String password, ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> data) {
		this.setEmail(email);
		this.setPassword(password);
		this.setData(data);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> getData() {
		return data;
	}

	public void setData(ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> data) {
		this.data = data;
	}
}
