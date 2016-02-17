package com.example.kristinektran.kristinecalendar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Encapsulates fetching the forecast and displaying it as a {@link ListView} layout.
 */
public class CalendarFragment extends Fragment {
    private ArrayAdapter<String> mCalendarAdapter;

    public CalendarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create some dummy data for the ListView.  Here's a sample weekly forecast
        String[] data = {
                "KRISTINE EVENT 1",
                "KRISTINE EVENT 2",
                "KRISTINE EVENT 3",
                "KRISTINE EVENT 4",
                "KRISTINE EVENT 5",
                "KRISTINE EVENT 6",
                "KRISTINE EVENT 7"
        };
        List<String> eventCalendar = new ArrayList<String>(Arrays.asList(data));

        // Now that we have some dummy forecast data, create an ArrayAdapter.
        // The ArrayAdapter will take data from a source (like our dummy forecast) and
        // use it to populate the ListView it's attached to.
        mCalendarAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_calendar, // The name of the layout ID.
                        R.id.list_item_calendar_textview, // The ID of the textview to populate.
                        eventCalendar);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_calendar);
        listView.setAdapter(mCalendarAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String forecast = mCalendarAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, forecast);
                startActivity(intent);
            }
        });

        return rootView;
    }
}

