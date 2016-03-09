package com.cse110.apk404.myCalendar;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Debug;
import android.util.Log;

import com.cse110.apk404.myCalendar.eventListHandler.CalendarDB;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarObject;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarObjectList;
import com.cse110.apk404.myCalendar.eventListHandler.EventListHandler;
import com.cse110.apk404.myCalendar.eventListHandler.StaticEvent;
import com.cse110.apk404.myCalendar.eventListHandler.StaticEventList;

import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * A singleton class used for detecting application on start and on exit for
 * loading and saving lists to database
 * This does not work correctly, onCreate() works but onTerminate() only works for emulator
 */
public class myCalendarApplication extends Application {
    private static myCalendarApplication myCalendarApp;

    public myCalendarApplication getInstance() {
        return myCalendarApp;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myCalendarApp = this;
        Log.d("ApplicationStatus", "App started");

        // TODO - This function gets called when app is created, initialize database here to load lists to memory

        // doesn't work cuz I am getting error saying null array
        try {
//            CalendarDB.initDBLocal(this);
            EventListHandler.initStaticList(); // Intialize list once at the begining
            EventListHandler.initDynamicList();
//            EventListHandler.getStaticList().setList((ArrayList<StaticEvent>) CalendarDB.retriveListLocal(1).getList()); // Load lists from database to EventListHandler
        } catch (Exception e) {
            Log.e("Error04", e.getMessage());
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("ApplicationStatus", "App terminated");

//        try {
//            CalendarDB.updateListLocal(1, EventListHandler.getStaticList()); // Save lists from EventListHandler to database
//        } catch (Exception e) {
//            Log.e("Error05", e.getMessage());
//        }
    }
}