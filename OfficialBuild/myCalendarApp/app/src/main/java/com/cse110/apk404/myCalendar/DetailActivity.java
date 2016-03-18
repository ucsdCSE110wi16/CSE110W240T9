package com.cse110.apk404.myCalendar;

import java.text.SimpleDateFormat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cse110.apk404.myCalendar.CalendarEvent;
import com.cse110.apk404.myCalendar.EventListHandler;
import com.cse110.apk404.myCalendar.StaticEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Event detailed description that contains a delete button to delete this event
 * and a finished button to set the state of this event to be finished
 */
public class DetailActivity extends AppCompatActivity {

    // Intent keys
    static final String IS_EDIT_EVENT = "IS_EDIT_EVENT";
    static final String ID = "ID";


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
        final long id = mIntent.getLongExtra("ID", 0);

        event = EventListHandler.getEventById(id);

        /* Floating action button */
        fab = (FloatingActionButton) findViewById(R.id.fab_finished_event);
        /* Add Snackbar on click */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    Snackbar.make(view, "Event is being edited", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                // TODO - set the event to be finished here then resume parent activity


                fab.hide();

                // If the edit button is clicked, then go to add event activity
                Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                Bundle extras = new Bundle();
                extras.putBoolean(IS_EDIT_EVENT,true);
                extras.putLong(ID,id);
                intent.putExtras(extras);

//                intent.putExtras("isEditEvent", true);
//                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        // If event is finished, get rid of the editing button
        if (event.isFinished()) {
            fab.hide();
        }

        // Replace the home back button with delete button
        final Drawable closeIcon = getResources().getDrawable(R.drawable.ic_close_white_24dp);
        closeIcon.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(closeIcon);


        String event_Color = event.getColor();
        String event_Name = event.getName();
        String event_location = event.getLocation();

        // Use a date formatter to get the correct date format
        DateFormat time = new SimpleDateFormat("MM/dd/yyyy  HH:mm");
        String event_time = "";
        if (event.isStatic()) {
            event_time = "From: " + time.format(event.getStartTime().getTime()) + "\n" +
                    "     To: " + time.format(event.getEndTime().getTime());
        } else {
            event_time = "Time Estimate Length: " + ((DynamicEvent)event).getEstimatedLength() / 60 + " Hours\n" +
                    "     Deadline: " + time.format(((DynamicEvent)event).getDeadline().getTime());
        }

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
        int eventColor = Color.parseColor(event_Color);
        int darkerEventColor = Utils.darker(eventColor, 0.8f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(darkerEventColor);
            toolbar.setBackgroundColor(eventColor);
        }

        if (!event.isFinished()) {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            fab.setImageTintList(ColorStateList.valueOf(eventColor));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to tool bar if it is present.
        getMenuInflater().inflate(R.menu.activity_detail, menu);

        // Limie menu items
        if (event.isFinished()) {
            menu.findItem(R.id.action_edit_event).setVisible(false);
            menu.findItem(R.id.action_finish_event).setVisible(false);
        } else {
            menu.findItem(R.id.action_edit_event).setVisible(false);
            menu.findItem(R.id.action_unfinish_event).setVisible(false);
        }

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
                try {
                    EventListHandler.removeEventById(event.getId());
                } catch (Exception e) {
                    Log.e("Error06", e.getMessage());
                }
                RestartMainActivity(0, "");
                return true;
            case R.id.action_finish_event:
                // Archive event
                if (event != null) {
                    event.setFinished(true);
                    RestartMainActivity(0, "Event is marked as finished and archived");
                }
                return true;
            case R.id.action_unfinish_event:
                // Set event as active again and reload current activity
                if (event != null) {
                    event.setFinished(false);
//                    finish();
//                    startActivity(getIntent());
                    RestartMainActivity(0, "Event is set to active again");
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void RestartMainActivity(int timeToRestartInMilliseconds, String snackbarMessgae) {
        // Restart parent activity to refresh calendar list UI
        if (!snackbarMessgae.isEmpty())
//            Snackbar.make(findViewById(android.R.id.content), snackbarMessgae, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        fab.hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, timeToRestartInMilliseconds);
    }

    @Override
    public void onBackPressed()
    {
        // Comment this super call to avoid calling finish() in the physical back button
        // super.onBackPressed();
    }

}
