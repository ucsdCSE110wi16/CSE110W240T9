package com.cse110.apk404.myCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import android.app.Fragment;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.cse110.apk404.myCalendar.R;
import com.cse110.apk404.myCalendar.CalendarEvent;
import com.cse110.apk404.myCalendar.DynamicEvent;
import com.cse110.apk404.myCalendar.EventListHandler;
import com.cse110.apk404.myCalendar.StaticEvent;
import com.cse110.apk404.myCalendar.apiclient.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * This is a calender view fragment that extends the CalendarViewBaseFragment and
 * override onMonthChange() to pull events from the database to be displayed in the
 * MainActivity.
 */
public class CalendarViewFragment extends CalendarViewBaseFragment {

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        String finisehdEventColor = "#DCDCDC";

        // Populate the week view with some events.
        List<WeekViewEvent> event_list_UI = new ArrayList<WeekViewEvent>();

        ArrayList<? extends CalendarEvent> static_event_list = new ArrayList<>();
        ArrayList<? extends CalendarEvent> dynamic_event_list = new ArrayList<>();

        try {
            static_event_list = EventListHandler.getStaticList().getList();
            dynamic_event_list = EventListHandler.getDynamicList().getList();
            Log.d("EventLength", static_event_list.size() + "");

        } catch (Exception e) {
            Log.e("Error03", e.getMessage());
        }

        // Loop through the static events and populate event_list_UI when the loaded month
        // is the specified newMonth, if not, three of the same events would be loaded
        for (int i = 0; i < static_event_list.size(); i++) {
            StaticEvent event_temp = (StaticEvent) static_event_list.get(i);

            DateFormat time = new SimpleDateFormat("MM");
            int event_month = Integer.parseInt(time.format(event_temp.getStartTime().getTime()));

            // Set past event as finished
            if(event_temp.getEndTime().before(Calendar.getInstance()) && !event_temp.isFinished()) {
                event_temp.setFinished(true);
            }
            if (event_month == newMonth) {
                String description = "";
                description += event_temp.getName();
                if (!event_temp.getLocation().equals("")) {
                    description += " - " + event_temp.getLocation();
                }
                WeekViewEvent event = new WeekViewEvent(event_temp.getId(), description, event_temp.getStartTime(), event_temp.getEndTime());
                if (event_temp.isFinished()) {
                    event.setColor(Color.parseColor(finisehdEventColor));
                } else {
                    event.setColor(Color.parseColor(event_temp.getColor()));
                }
                event_list_UI.add(event);
            }
        }

        // Loop through dynamic event list and load UI.
        for (int i = 0; i < dynamic_event_list.size(); i++) {
            DynamicEvent event_temp = (DynamicEvent) dynamic_event_list.get(i);

            DateFormat time = new SimpleDateFormat("MM");
            int event_month = Integer.parseInt(time.format(event_temp.getStartTime().getTime()));

            // Set past event as finished
            if(event_temp.getDeadline().before(Calendar.getInstance()) && !event_temp.isFinished()) {
//                event_temp.setFinished(true);

                // if dynamic event is passed simply delete it
                try {
                    EventListHandler.removeEventById(event_temp.getId());
                }catch (Exception e){
                    Log.e("Error02", e.getMessage());
                }

            }
            if (event_month == newMonth) {
                String description = "";
                description += event_temp.getName();
                if (!event_temp.getLocation().equals("")) {
                    description += " - " + event_temp.getLocation();
                }
                WeekViewEvent event = new WeekViewEvent(event_temp.getId(), description, event_temp.getStartTime(), event_temp.getEndTime());

                if (event_temp.isFinished()) {
                    event.setColor(Color.parseColor(finisehdEventColor));
                } else {
                    event.setColor(Color.parseColor(event_temp.getColor()));
                }
                event_list_UI.add(event);
            }
        }


        return event_list_UI;
    }
}
