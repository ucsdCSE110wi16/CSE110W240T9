package com.cse110.apk404.myCalendar;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Debug;
import android.util.Log;

import com.cse110.apk404.myCalendar.eventListHandler.CalendarDB;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarObject;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarObjectList;
import com.cse110.apk404.myCalendar.eventListHandler.DynamicEvent;
import com.cse110.apk404.myCalendar.eventListHandler.EventListHandler;
import com.cse110.apk404.myCalendar.eventListHandler.StaticEvent;
import com.cse110.apk404.myCalendar.eventListHandler.StaticEventList;

import java.io.IOException;
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


        // doesn't work cuz I am getting error saying null array
        try {

            // TODO all three lists !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // Intialize list once at the begining of each loading
            EventListHandler.initStaticList();
            //EventListHandler.initDynamicList();
            // EventListHanlder.initDeadlineList();

            // Load lists from database to EventListHandler
            CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list0 = CalendarDB.retriveListLocal(0, this);
            //CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list1 = CalendarDB.retriveListLocal(1, this);
            //CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list2 = CalendarDB.retriveListLocal(1, this);

            EventListHandler.setStaticList((StaticEventList) list0);
            //EventListHandler.getDynamicList().setList((ArrayList<DynamicEvent>) list0.getList());
            //EventListHandler.getDynamicList().setList((ArrayList<DynamicEvent>) list0.getList());

        } catch (Exception e) {
            Log.e("Fuck", e.getMessage());
            try {

                // for the very first time, initialize the local database
                CalendarDB.initDBLocal(this);

            } catch (IOException ex) {
                Log.e("Errorrrrr", e.getMessage());
            }
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
    }
}