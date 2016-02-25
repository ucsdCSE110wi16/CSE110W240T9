package com.cse110.apk404.myCalendar;

import android.app.TimePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;


/**
 * Event detailed description that contains a delete button to delete this event
 * and a finished button to set the state of this event to be finished
 */
public class AddEventActivity extends AppCompatActivity {

    Toolbar toolbar = null;


    private Button done;
    private Button setStart;
    private Button setEnd;



    DateFormat fmtTime=DateFormat.getDateTimeInstance();
    //    TextView timeLabel;
    Calendar time=Calendar.getInstance();

    /*the setDate funtion*/
    public void setDate(View view)
    {
        PickerDialogs pickerDialogs = new PickerDialogs();
        pickerDialogs.show(getSupportFragmentManager(),"date_picker");
    }

    TimePickerDialog.OnTimeSetListener timePicker =new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay,
                              int minute) {
            time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            time.set(Calendar.MINUTE, minute);
//            timeLabel.setText(fmtTime.format(time.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


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
//                Snackbar.make(view, "Event is created", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                fab.hide();

                // TODO - set the event to be finished here then resume parent activity


                // Wait 2 seconds, then create the event and resume parent activity (calendar view)
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);
            }
        });


         /* Creates dropdown for type of event */
        Spinner dropdown = (Spinner) findViewById(R.id.typeOfEvent);
        String[] items = new String[]{"Dynamic", "Static-Not Periodic", "Static-Periodic"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        /* Dropdown for color */
        Spinner colorPicker = (Spinner) findViewById(R.id.colorDropdown);
        String[] colors = new String[]{"Red", "Blue", "Green"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, colors);
        colorPicker.setAdapter(adapter2);


        setStart=(Button)findViewById(R.id.startTime);

        setStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, timePicker,
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE), true).show();
            }
        });

        setEnd=(Button)findViewById(R.id.endTime);

        setEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, timePicker,
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE), true).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to tool bar if it is present.
        getMenuInflater().inflate(R.menu.activity_add_event, menu);

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
