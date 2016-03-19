package com.cse110.apk404.myCalendar;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DynamicEventList implements CalendarObjectList<ArrayList<DynamicEvent>, DynamicEvent> {

    private static final long serialVersionUID = 1L;

    private ArrayList<DynamicEvent> dynamicList; // an ArrayList to store the dynamic events after they are sorted

    public DynamicEventList() {
        dynamicList = new ArrayList<DynamicEvent>();
    }

    public ArrayList<DynamicEvent> getList() {
        return dynamicList;
    }

    public void setList(ArrayList<DynamicEvent> list) {
        this.dynamicList = list;
    }

    public boolean addEvent(DynamicEvent event) throws CalendarError {
        boolean check = true;
        if (event == null) {
            check = false;
            throw new CalendarError("Null Event");
        }

        this.dynamicList.add(event);
        return true;
    }

    public boolean removeEventById(Long id) throws CalendarError {
        boolean check = false;
        if (id == null)
            throw new CalendarError("Null Event");
        DynamicEvent eventToRemove;
        if (dynamicList == null) return check;
        for (int i = 0; i < dynamicList.size();) {
            eventToRemove = dynamicList.get(i);
            if (eventToRemove.getId() == id) {
                System.out.println(id);
                boolean removeSomeMore = dynamicList.remove(eventToRemove);
                while (removeSomeMore) {
                    removeSomeMore = dynamicList.remove(eventToRemove);
                }
                check = true;
            } else {
                i++;
            }
        }


        return check;
    }

    public DynamicEvent findLastEventById(Long id) throws CalendarError {
        if (id == null)
            throw new CalendarError("Null Event");
        DynamicEvent eventToFind;
        if (dynamicList == null) return null;
        for (int i = 0; i < dynamicList.size(); i++) {
            eventToFind = dynamicList.get(i);
            if (eventToFind.getId() == id) {
                return dynamicList.get(i);
            }
        }
        return null;
    }

//	public DynamicEvent findFirstEventById(Long id) throws CalendarError {
//		if (id == null)
//			throw new CalendarError("Null Event");
//		DynamicEvent eventToFind;
//		if(dynamicList == null) return null;
//			eventToFind = dynamicList.get(0);
//			if (eventToFind.getId() == id){
//				return dynamicList.get(0);
//			}
//		return null;
//	}

    public boolean checkEndtimeDeadline() throws CalendarError {

        Log.e("shit", "" + dynamicList.size());
        for (int i = 0; i < dynamicList.size(); i++) {
            Log.e("shit", "one check");
            System.out.println("Endtime: " + dynamicList.get(i).getEndTime().get(Calendar.HOUR_OF_DAY) + ":" + dynamicList.get(i).getEndTime().get(Calendar.MINUTE)
                    + " Deadline: " + dynamicList.get(i).getDeadline().get(Calendar.HOUR_OF_DAY) + ":" + dynamicList.get(i).getDeadline().get(Calendar.MINUTE));
            if (!dynamicList.get(i).getEndTime().before(dynamicList.get(i).getDeadline())) {
                Log.e("shit", "wow, true here");

                return false;
            }
        }
        return true;
    }

//	public DynamicEvent findLastEvent() throws CalendarError {
//		Date end;
//		DateFormat date;
//		date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//		int lastElement = dynamicList.size();
//		if(lastElement != 0) {
//			end = dynamicList.get(lastElement-1).getEndTime().getTime();
//			System.out.println("LAST ELEMENT The start date is: " + date.format(end)  + " " + dynamicList.get(lastElement - 1).getName() );
//			return dynamicList.get(lastElement - 1);
//		}
//
//		else
//			return null;
//	}

    public void print() {
        DynamicEvent de;
        Date start;
        Date end;
        DateFormat date;
        int size = dynamicList.size();
        System.out.println("DynamicList Size: " + dynamicList.size());
        for (int i = 0; i < size; i++) {
            de = dynamicList.get(i);
            start = de.getStartTime().getTime();
            end = de.getEndTime().getTime();
            date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            System.out.println("FINAL LIST The start date is: " + date.format(start) + " " + dynamicList.get(i).getName() + dynamicList.get(i).getId());
            System.out.println("FINAL LIST The end date is: " + date.format(end) + " " + dynamicList.get(i).getName() + dynamicList.get(i).getId());
        }
    }


}