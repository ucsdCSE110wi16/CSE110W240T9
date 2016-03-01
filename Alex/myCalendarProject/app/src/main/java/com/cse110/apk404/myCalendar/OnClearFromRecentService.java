package com.cse110.apk404.myCalendar;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.cse110.apk404.myCalendar.eventListHandler.CalendarDB;
import com.cse110.apk404.myCalendar.eventListHandler.EventListHandler;
import com.cse110.apk404.myCalendar.eventListHandler.StaticEvent;

import java.util.ArrayList;

/**
 *
 * A singleton class used for detecting application on start and on exit for
 * loading and saving lists to database
 * First, register this service in Manifest.xml via
 * <service android:name="com.example.OnClearFromRecentService" android:stopWithTask="false" />
 * Then start this service either on splash activity or mainActivity
 * startService(new Intent(getBaseContext(), OnClearFromRecentService.class));
 * And now whenever you will clear your app from android recent Then this method onTaskRemoved() will execute.
 */
public class OnClearFromRecentService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d("ClearFromRecentService", "Service Started");
//
//        // Load events to EventListHandler from database
//
//        return START_NOT_STICKY;
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ClearFromRecentService", "Service Destroyed");

        saveEventsToDatabase();

    }

    public void onTaskRemoved(Intent rootIntent) {
        Log.e("ClearFromRecentService", "END");

        saveEventsToDatabase();

        stopSelf();
    }

    private void saveEventsToDatabase() {
        // Save events from EventListHandler to database

        // TODO - Save lists from memory to database in here, it gets called when the app is killed

        // Doesn't work getting error saying trying to access null array
        try {
            Log.d("ListSize1", EventListHandler.getStaticList().getList().size() + "");
            CalendarDB.updateListLocal(1, EventListHandler.getStaticList()); // Save lists from EventListHandler to database
            Log.d("ListSize2", EventListHandler.getStaticList().getList().size() + "");
        } catch (Exception e) {
            Log.e("Error05", e.getMessage());
        }

    }
}