package com.cse110.apk404.myCalendar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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


/**
 * Event detailed description that contains a delete button to delete this event
 * and a finished button to set the state of this event to be finished
 */
public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar = null;
    DetailActivity activity = this;

    TextView eventNameText;
    TextView eventLocationText;
    TextView eventTimeText;
    TextView eventDescriptionText;


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

        /* Floating action button */
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_finished_event);
        /* Add Snackbar on click */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Event is archived", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                fab.hide();

                // TODO - set the event to be finished here then resume parent activity


                // Wait 2 seconds, then finish and resume parent activity (calendar view)
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1200);
            }
        });

        // Get the id from intent then get the event detail from the intent
        Intent mIntent = getIntent();
        int id = mIntent.getIntExtra("id", 0);

        // TODO - get event from event list use id

        // If the android version supports, we can also change the tool bar color and fab color to
        // match the color of the event
        int eventColor = getResources().getColor(R.color.colorGreen); //get color form event
        int darkerEventColor = Utils.darker(eventColor, 0.7f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(darkerEventColor);
            toolbar.setBackgroundColor(eventColor);
        }
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        fab.setImageTintList(ColorStateList.valueOf(eventColor));

        eventNameText = (TextView) findViewById(R.id.event_name);
        eventLocationText = (TextView) findViewById(R.id.event_location);
        eventTimeText = (TextView) findViewById(R.id.event_time);
        eventDescriptionText = (TextView) findViewById(R.id.event_description);

        eventNameText.setText("Event Name (ID): " + id);
        eventLocationText.setText("Center Hall UCSD");
        eventTimeText.setText("10:30AM - 12:00PM");
        eventDescriptionText.setText("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Vivamus ut fermentum sem. Fusce ut erat risus. " +
                "Fusce nulla justo, tempor id massa vitae, tincidunt porttitor purus. Donec vulputate " +
                "sagittis tellus at pulvinar. Nunc quis ultrices dui. Mauris laoreet finibus nulla at " +
                "dapibus. Proin dapibus molestie tincidunt. Duis tempor facilisis est quis molestie. " +
                "Nulla volutpat, arcu ac hendrerit malesuada, nibh odio volutpat dolor, vitae tristique" +
                " eros nisl eget arcu. Sed porta aliquet dui, in varius odio ultrices eget. Ut a magna " +
                "nunc. Sed tempus auctor ex, placerat condimentum purus dictum ac.venenatis elit. Ut sit " +
                "amet congue libero, eu euismod tortor. Praesent nunc metus, ultrices at nunc eget, grav" +
                "ida maximus odio. Fusce pulvinar purus et hendrerit lacinia. Interdum et malesuada fames" +
                " ac ante ipsum primis in faucibus.");
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
                return true;
            case R.id.action_unfinish_event:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
