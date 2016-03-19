package com.cse110.apk404.myCalendar.sampleReference;

import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;
import com.cse110.apk404.myCalendar.R;
import com.cse110.apk404.myCalendar.apiclient.Event;
import com.cse110.apk404.myCalendar.apiclient.MyJsonService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * An example of how events can be fetched from network and be displayed on the week view.
 */
public class AsynchronousActivity extends BaseActivity implements Callback<List<Event>> {

    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    boolean calledNetwork = false;

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Download events from network if it hasn't been done already. To understand how events are
        // downloaded using retrofit, visit http://square.github.io/retrofit
        if (!calledNetwork) {
            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint("https://api.myjson.com/bins")
                    .build();
            MyJsonService service = retrofit.create(MyJsonService.class);
            service.listEvents(this);
            calledNetwork = true;
        }

        // Return only the events that matches newYear and newMonth.
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    /**
     * Checks if an event falls into a specific year and month.
     * @param event The event to check for.
     * @param year The year.
     * @param month The month.
     * @return True if the event matches the year and month.
     */
    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    @Override
    public void success(List<Event> events, Response response) {
        this.events.clear();
        for (Event event : events) {
            this.events.add(event.toWeekViewEvent());
        }
        getWeekView().notifyDatasetChanged();
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();
        Toast.makeText(this, R.string.async_error, Toast.LENGTH_SHORT).show();
    }
}
