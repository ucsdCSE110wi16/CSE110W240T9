package com.cse110.apk404.myCalendar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cse110.apk404.myCalendar.eventListHandler.CalendarEvent;
import com.cse110.apk404.myCalendar.eventListHandler.EventListHandler;
import com.cse110.apk404.myCalendar.eventListHandler.StaticEvent;


/**
 * Event detailed description that contains a delete button to delete this event
 * and a finished button to set the state of this event to be finished
 */
public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar = null;
    FloatingActionButton fab = null;

    TextView eventNameText;
    TextView eventLocationText;
    TextView eventTimeText;
    TextView eventDescriptionText;

    CalendarEvent event = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /* Toolbar */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Enable back button in toolbar */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Get the id from intent then get the event detail from the intent
        Intent mIntent = getIntent();
        long id = mIntent.getLongExtra("id", 0);

        event = EventListHandler.getEventById(id);

        // If event is finished, get rid of the editing button
        if (!event.isFinished()) {
        /* Floating action button */
            fab = (FloatingActionButton) findViewById(R.id.fab_finished_event);
        /* Add Snackbar on click */
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Event is marked as finished and archived", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    fab.hide();
//                fab.setRippleColor();

                    // TODO - set the event to be finished here then resume parent activity


                    // Wait 2 seconds, then finish and resume parent activity (calendar view)
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1500);
                }
            });
        }

        // Replace the home back button with delete button
        final Drawable closeIcon = getResources().getDrawable(R.drawable.ic_close_white_24dp);
        closeIcon.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(closeIcon);


        String event_Color = event.getColor();
        String event_Name = event.getName();
        String event_location = event.getLocation();
        String event_time = "" + event.getStartTime().getHour() + ":" + event.getStartTime().getMinute()
                + " - " + event.getEndTime().getHour() + ":" + event.getEndTime().getMinute();
        String event_description = event.getDescription();

        eventNameText = (TextView) findViewById(R.id.event_details_title); // update name in nav bar
        eventLocationText = (TextView) findViewById(R.id.event_location);
        eventTimeText = (TextView) findViewById(R.id.event_time);
        eventDescriptionText = (TextView) findViewById(R.id.event_description);

        eventNameText.setText(event_Name);
        eventLocationText.setText(event_location);
        eventTimeText.setText(event_time);
        eventDescriptionText.setText(event_description);

        // If the android version supports, we can also change the tool bar color and fab color to
        // match the color of the event
        setToolbarStyle(event_Color, fab, toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to tool bar if it is present.
        getMenuInflater().inflate(R.menu.activity_detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_delete_event:
                return true;
            case R.id.action_finish_event:
                if (event != null) event.setFinished(true);
                return true;
            case R.id.action_unfinish_event:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Change navigabtion bar background to event_color and tint the navigation bar text event_color
     *
     * @param event_Color the color to tint
     * @param fab         floating action button
     * @param toolbar     the tool bar to change
     * @return true if color changed successfully
     */
    public boolean setToolbarStyle(String event_Color, FloatingActionButton fab, Toolbar toolbar) {
        if (fab == null) return false;

        int eventColor = Color.parseColor(event_Color);
        int darkerEventColor = Utils.darker(eventColor, 0.8f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(darkerEventColor);
            toolbar.setBackgroundColor(eventColor);
        }
//        fab.setBackgroundTintList(ColorStateList.valueOf(eventColor));
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        fab.setImageTintList(ColorStateList.valueOf(eventColor));

        return true;
    }


}
