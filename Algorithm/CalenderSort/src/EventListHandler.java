//package com.cse110.apk404.myCalendar.eventListHandler;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class EventListHandler {


    private static StaticEventList staticList = null;
    private static DynamicEventList dynamicList = null;
    private static ArrayList<CalendarEvent> events = null; //list to store all events in one given day
    private static Calendar startTimeOfDay;
    private static Calendar endTimeOfDay;


    public static ArrayList<CalendarEvent> getEvents() {
        return events;
    }

    public static void setEvents(ArrayList<CalendarEvent> events) {
        EventListHandler.events = events;
    }

    public static CalendarEvent getEventById(long Id) {

        ArrayList<StaticEvent> staticArrayList = staticList.getList();
        if (staticArrayList != null) {
            for (int i = 0; i < staticArrayList.size(); i++) {
                if (staticArrayList.get(i).getId() == Id) {
                    return staticArrayList.get(i);
                }
            }
        }
        ArrayList<DynamicEvent> dynamicArrayList = dynamicList.getList();
        if (dynamicArrayList != null) {
            for (int i = 0; i < dynamicArrayList.size(); i++) {
                if (dynamicArrayList.get(i).getId() == Id) {
                    return dynamicArrayList.get(i);
                }
            }
        }

        return null;
    }

    public static void initStaticList() {
        if (staticList == null) staticList = new StaticEventList();
    }

    public static void initDynamicList() {
        dynamicList = new DynamicEventList();
    }

    public static StaticEventList getStaticList() {
        return staticList;
    }

    public static void setStaticList(StaticEventList staticList) {
        EventListHandler.staticList = staticList;
    }

    public static DynamicEventList getDynamicList() {
        return dynamicList;
    }

    public static void setDynamicList(DynamicEventList dynamicList) {
        EventListHandler.dynamicList = dynamicList;
    }


    public static boolean checkValidTime(Calendar startTime, Calendar endTime) {
        if (startTime.compareTo(endTime) >= 0) {
            return false;
        }
        return true;
    }

    //Create a static event to add to the static event list
    public static boolean createStaticEvent(String name, String location, Calendar startTime, Calendar endTime,
                                            boolean isStatic, boolean isPeriodic, boolean isFinished, String description, String color) throws CalendarError {
        //check if start and end times are valid
        boolean check = false;
        if (!checkValidTime(startTime, endTime)) {
//            System.out.println("Fail");
            return false;
        }
        //check if event is static
        if (isStatic == false)
            return false;

        StaticEvent staticEvent = new StaticEvent(name, location, startTime, endTime, isStatic,
                isPeriodic, isFinished, description, color);
        staticEvent.setId(System.currentTimeMillis());
        check = staticList.addEvent(staticEvent);

        return (check);
    }


    public static boolean removeEventById(long temp) throws CalendarError {
        boolean check = true;
        if (staticList == null) {
            check = false;
            return check;
        }
        check = staticList.removeEventById(temp);
        return check;
    }

    ;

    //Create a dynamic event to add to the dynamic event list
    public static void createDynamicEvent(String name, int estimatedLength, boolean isStatic,
                                          Calendar deadline, boolean isFinished, String description) {
        return;
    }

    
    //Dynamic sort algorithm
    public static boolean dynamicSort() throws CalendarError {

        Comparator<StaticEvent> staticcomparator = new Comparator<StaticEvent>() {

            @Override
            public int compare(StaticEvent o1, StaticEvent o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        };

        Comparator<DynamicEvent> comparator = new Comparator<DynamicEvent>() {

            @Override
            public int compare(DynamicEvent o1, DynamicEvent o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        };

        Comparator<DynamicEvent> reversecomparator = new Comparator<DynamicEvent>() {

            @Override
            public int compare(DynamicEvent o1, DynamicEvent o2) {
                return -o1.getDeadline().compareTo(o2.getDeadline());
            }
        };


        PriorityQueue<DynamicEvent> currDynamicEList = new PriorityQueue<DynamicEvent>(1,comparator);
        PriorityQueue<DynamicEvent> reverseDynamicEList = new PriorityQueue<DynamicEvent>(1,reversecomparator);
        PriorityQueue<StaticEvent> currStaticEList = new PriorityQueue<StaticEvent>(1,staticcomparator);
        PriorityQueue<StaticEvent> sortedStaticEList = new PriorityQueue<StaticEvent>(1,staticcomparator);
        PriorityQueue<StaticEvent> freeList = new PriorityQueue<StaticEvent>();
        PriorityQueue<StaticEvent> sortedfreeList = new PriorityQueue<StaticEvent>();
        ArrayList<StaticEvent> staticArrayList = staticList.getList();
        ArrayList<DynamicEvent> dynamicArrayList = null;
        
        if (dynamicList != null) {
            dynamicArrayList = dynamicList.getList();
        }

        if (staticArrayList != null) {
            for (int i = 0; i < staticArrayList.size(); i++) {
                if (!staticArrayList.get(i).isFinished()) {
                    currStaticEList.add(staticArrayList.get(i));
                }
            }
        }
        if (dynamicArrayList != null) {
            for (int i = 0; i < dynamicArrayList.size(); i++) {
                if (!dynamicArrayList.get(i).isFinished()) {
                    currDynamicEList.add(dynamicArrayList.get(i));
                }
            }
        }

//        StaticEvent event;
//        while(!currStaticEList.isEmpty()) {
//            DateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//           event = currStaticEList.poll();
//             Date date = event.getEndTime().getTime();
//            System.out.println("currStaticEList: ");
//            System.out.println(time.format(date));
//
//
//        }
        
        EventListHandler.checkConflict(currStaticEList, sortedStaticEList);
        int size = sortedStaticEList.size();
           for(int i=0; i< size; i++) {
        	   System.out.println(sortedStaticEList.size());
                DateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
               
                Date date = sortedStaticEList.poll().getEndTime().getTime();
                System.out.println(time.format(date));
//                sortedStaticEList.add(event);
            }
        
        
        

        return false;
    }

    /*checks if there are conflicts in the static time table, if there are conflicts, return the two conflicted
    events as a single event*/
    private static void checkConflict(PriorityQueue<StaticEvent> currStaticEList, PriorityQueue<StaticEvent> sortedStaticEList)
            throws CalendarError {
        
        StaticEvent firstCheck = null;
        StaticEvent secondCheck = null;
        Calendar newStartTime = null;
        Calendar newEndTime = null;
        while (!currStaticEList.isEmpty()) {
          
        	firstCheck = currStaticEList.poll();

 
//            System.out.println("first");
//            
//            DateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            
//            Date date = firstCheck.getEndTime().getTime();
//            
//            System.out.println(time.format(date));
//            

            
            if (!currStaticEList.isEmpty()) {
            	
                secondCheck = currStaticEList.peek();
            
            
            if (firstCheck.getEndTime().compareTo(secondCheck.getStartTime()) >= 0) {
            	System.out.println("Conflict");
            	
            	if (firstCheck.getStartTime().compareTo(secondCheck.getStartTime()) <= 0){
            		newStartTime = firstCheck.getStartTime();
            	}
            	else {
            		newStartTime = secondCheck.getStartTime();
            	}
            	if (firstCheck.getEndTime().compareTo(secondCheck.getEndTime()) >= 0){
            		newEndTime = firstCheck.getEndTime();
            	}
            	else {
            		newEndTime = secondCheck.getEndTime();
            	}
            	StaticEvent newevent = new StaticEvent(firstCheck.getName(),
                        firstCheck.getLocation(), newStartTime, newEndTime,
                        firstCheck.isStatic(), firstCheck.isPeriodic(), firstCheck.isFinished(),
                        firstCheck.getDescription(), firstCheck.getColor());
            	//remove secondCheck
                currStaticEList.poll();
                //add newEvent back to current list to check for next conflict
                currStaticEList.add(newevent);
            
            }
            else{
            	sortedStaticEList.add(firstCheck);
            	firstCheck = null;
            	secondCheck = null;
            }
            }
            else{
            	sortedStaticEList.add(firstCheck);
            }
        }
     
    }

    //#1 on the order list
    //remove static time that are before and after 9a/p
    public static void purgeStaticList(PriorityQueue<StaticEvent> sortedStaticEList) {
    	StaticEvent time;

        while (!sortedStaticEList.isEmpty()) {

            //if earlier than 9am set to 9am
            time = sortedStaticEList.peek();
            //if the start and end time are both earlier than 9am
            if (time.getEndTime().compareTo(startTimeOfDay) < 0) {
                sortedStaticEList.poll();
                continue;
            }
            //if start is earlier than 9am but end time is later than 9 am
            if (time.getStartTime().compareTo(startTimeOfDay) < 0 && time.getEndTime().compareTo(startTimeOfDay) > 0) {
                time.setStartTime(startTimeOfDay);
                continue;
            }

            //if the start and end time are both later than 9pm
            if (time.getStartTime().compareTo(endTimeOfDay) > 0) {
                sortedStaticEList.poll();
                continue;
            }
            //if start is earlier than 9pm but end time is later than 9pm
            if (time.getStartTime().compareTo(endTimeOfDay) < 0 && time.getEndTime().compareTo(endTimeOfDay) > 0) {
                time.setEndTime(endTimeOfDay);
                continue;
            }
        }
    }

    
    private static int daysBetween(Calendar d1, Calendar d2) {
        return (int) (Math.abs(d2.getTime().getTime() - d1.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    //#2 on the purge list
    //returns false if no free time, true will write freetime to freeList
    private static boolean updateFreeTime(PriorityQueue<StaticEvent> sortedStaticEList, PriorityQueue<DynamicEvent> 
    reverseDynamicEvent, PriorityQueue<StaticEvent> freeList) throws CalendarError {

        //get deadline from last of currDynamicEvent
        DynamicEvent lastdynamicevent = reverseDynamicEvent.peek();

        Calendar currTime = Calendar.getInstance();
        Calendar lastDynamicTime = lastdynamicevent.getEndTime();


        //make freeTime block
        Calendar startTime = null;
        Calendar endTime = null;
        StaticEvent freeBlock = null;
        int days = EventListHandler.daysBetween(lastDynamicTime, currTime);
        for (int i = 0; i <= days; i++) {
            if (i == 0) {
                startTime = currTime;
            } else if (i == days) {
                endTime = lastDynamicTime;
            } else {
                startTime = startTimeOfDay;
                endTime = endTimeOfDay;
            }

            freeBlock = new StaticEvent("free time", "null", startTime, endTime,
                    true, false, false, "null", "null");
            freeList.add(freeBlock);
            startTime.add(Calendar.DAY_OF_MONTH, 1);
        }

        //init freetime for the peek operation
        StaticEvent freetime;

        //this while loop while get all the free time blocks from available times and store
        //the free times in freeList, loop condition is while the sorted static event list is not empty keep going
        while (!sortedStaticEList.isEmpty()) {
            //get temp as a staticEvent from the top of the sorted Q
            StaticEvent temp = sortedStaticEList.peek();
            //get freetime a staticEvent from the top of the freetime Q
            freetime = freeList.peek();

            //not enough time
            if (freetime == null)
                return false;

            //if the staticEvent's time is earlier the freeBlock's start, remove staticEvent from Q
            if (freetime.getStartTime().compareTo(temp.getEndTime()) >= 0) {
                sortedStaticEList.poll();
                continue;
            }

            //if the static events starts before the freetime block but ends after
            else if (freetime.getStartTime().compareTo(temp.getStartTime()) > 0 &&
                    (freetime.getStartTime()).compareTo(temp.getEndTime()) < 0) {
                //set the start of the freeTime block to be the end of the static event
                freetime.setStartTime(temp.getEndTime());
                //remove the static event
                sortedStaticEList.poll();
                continue;
            }

            //when we can actually start planning the freetime
            else if (freetime.getStartTime().compareTo(temp.getStartTime()) < 0) {
                //this case we dont want because the event ends later than our freetime
                if (temp.getEndTime().compareTo(freetime.getEndTime()) > 0) {
                    sortedStaticEList.poll();
                    continue;
                }
                //set start and end for the freetime to add to the Q
                temp.setStartTime(freetime.getStartTime());
                temp.setEndTime(temp.getStartTime());
                freeList.add(temp);
                //remove the static event to check from the Q
                sortedStaticEList.poll();
                //set starttime for next iteration
                freetime.setStartTime(temp.getEndTime());
                continue;
            }

            //if the start time of then static event if after the end of the free time block, remove static
            else if (freetime.getEndTime().compareTo(temp.getStartTime()) < 0) {
                sortedStaticEList.poll();
                continue;
            }
        }
        return true;
    }

    //#3 on the order list
    //function to strip freetime of blocks less than 30min, do not remove 10 min for now
    public static void purgefreeTime(PriorityQueue<StaticEvent> freeList, PriorityQueue<StaticEvent> sortedfreeList) {
        StaticEvent freetime;
        while (!freeList.isEmpty()) {
            freetime = freeList.peek();
            //confirm that this is indeed 30 min!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            if (freetime.getEndTime().compareTo(freetime.getStartTime()) < 30) {
                freeList.poll();
                continue;
            } else {
                sortedfreeList.add(freetime);
                freeList.poll();
                continue;
            }
        }
    }

	public static Calendar getStartTimeOfDay() {
		return startTimeOfDay;
	}

	public static void setStartTimeOfDay(Calendar startTimeOfDay) {
		EventListHandler.startTimeOfDay = startTimeOfDay;
	}

	public static Calendar getEndTimeOfDay() {
		return endTimeOfDay;
	}

	public static void setEndTimeOfDay(Calendar endTimeOfDay) {
		EventListHandler.endTimeOfDay = endTimeOfDay;
	}


}

/*
ArrayList<StaticEvent>() events = EventListHandler.getStaticEventsByDateKey(string dateKey); ...DONE
ArrayList<DynamicEvent>() events = EventListHandler.getDynamicEventsByDateKey(string dateKey);
StaticEvent se;
string id = se.getId();
EventHandler.removeEventById(id); ...DONE

setEventFinished(String Id)
addColor field to staticEvent.... Done
 */