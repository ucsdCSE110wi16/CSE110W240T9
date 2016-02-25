package com.cse110.apk404.myCalendar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Event detailed description that contains a delete button to delete this event
 * and a finished button to set the state of this event to be finished
 */
public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar = null;
    DetailActivity activity = this;

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
                }, 1000);
            }
        });

        // Get the id from intent then get the event detail from the intent
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("id", 0);

        // TODO - get event information details from event list



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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


}
