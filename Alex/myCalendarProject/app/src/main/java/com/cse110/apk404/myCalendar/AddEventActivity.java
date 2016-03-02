package com.cse110.apk404.myCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.cse110.apk404.myCalendar.eventListHandler.CalendarDB;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarObject;
import com.cse110.apk404.myCalendar.eventListHandler.CalendarObjectList;
import com.cse110.apk404.myCalendar.eventListHandler.EventListHandler;

import java.text.DateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


/**
 * Event detailed description that contains a delete button to delete this event
 * and a finished button to set the state of this event to be finished
 */
public class AddEventActivity extends AppCompatActivity {

    private Toolbar toolbar = null;
    private Button setStart = null;
    private Button setEnd = null;

    Calendar time = Calendar.getInstance();
    DateFormat timeFormatter = new SimpleDateFormat("HH:mm");

    TimePickerDialog.OnTimeSetListener startTimePicker = null;
    TimePickerDialog.OnTimeSetListener endTimePicker = null;

    final String[] colors = new String[]{"TEAL", "ORANGE", "PINK", "GREEN", "LIGHTGREEN", "BLUE", "PURPLE", "RED"};
    HashMap<String, String> eventColorMap = new HashMap<>();

    public static int startYear, startMonth, startDay, startHour, startMinute = 0;
    public static int endYear, endMonth, endDay, endHour, endMinute = 0;


    public void populateColorMap() {
        eventColorMap.put("RED", "#F44336");
        eventColorMap.put("ORANGE", "#FF5722");
        eventColorMap.put("PINK", "#E91E63");
        eventColorMap.put("GREEN", "#4CAF50");
        eventColorMap.put("LIGHTGREEN", "#8BC34A");
        eventColorMap.put("BLUE", "#2196F3");
        eventColorMap.put("PURPLE", "#9C27B0");
        eventColorMap.put("TEAL", "#009688");
    }

    /* it is called in OnClick function gets called when the setStartDate button is clicked */
    public void setStartDate(View view) {
        PickerDialogs pickerDialogs = new PickerDialogs();
        pickerDialogs.show(getSupportFragmentManager(), "date_picker");
    }

    /* it is called in OnClick function gets called when the setStartDate button is clicked */
    public void setEndDate(View view) {
        PickerDialogs2 pickerDialogs2 = new PickerDialogs2();
        pickerDialogs2.show(getSupportFragmentManager(), "date_picker");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        // Get Intent Info
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final Long ID = extras.getLong("ID"); // Return 0 if doesn't exist
        final Boolean IS_EDIT_EVENT = extras.getBoolean("IS_EDIT_EVENT");

        Log.d("AddEventIntentInfo", ID + "");
        Log.d("AddEventIntentInfo", IS_EDIT_EVENT + "");


        // Reset those static date int to 0 on start
        startYear = startMonth = startDay = startHour = startMinute = 0;
        endYear = endMonth = endDay = endHour = endMinute = 0;

        populateColorMap();

        /* Toolbar */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Enable back button in toolbar */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /* Floating action button */
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_finished_event);


        // Replace the home back button with delete button
        final Drawable closeIcon = getResources().getDrawable(R.drawable.ic_close_white_24dp);
        closeIcon.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(closeIcon);

        // Change toolbar style at the beginning
        setToolbarStyle("#009688", fab, toolbar);

         /* Creates dropdown for type of event */
        Spinner dropdown = (Spinner) findViewById(R.id.type_of_event_add_event);
        final String[] items = new String[]{"STATIC", "DYNAMIC"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        /* Dropdown for color */
        Spinner colorPicker = (Spinner) findViewById(R.id.color_dropdown_add_event);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, colors);
        colorPicker.setAdapter(adapter2);
        colorPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String color = eventColorMap.get(((Spinner) findViewById(R.id.color_dropdown_add_event)).getSelectedItem().toString().trim());
                setToolbarStyle(color, fab, toolbar);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        /*========= Time picker listeners and picker used to pick time =========*/

        setStart = (Button) findViewById(R.id.start_time_add_event);
        setEnd = (Button) findViewById(R.id.end_time_add_event);


        startTimePicker = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {

                setStart.setText("Start time: " + String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                startHour = hourOfDay;
                startMinute = minute;
            }
        };

        endTimePicker = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
//                Log.d("Log2", hourOfDay + "  " + minute);
                setEnd.setText("End time: " + String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                endHour = hourOfDay;
                endMinute = minute;
            }
        };

        /* Click on set start time button to make time picker pop up show up */
        setStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, startTimePicker,
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE), true).show();
                Context context = getApplicationContext();
            }
        });


        /* Click on set end time button to make time picker pop up show up */
        setEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, endTimePicker,
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE), true).show();
                Context context = getApplicationContext();
            }
        });

        /*========= Time picker listeners and picker used to pick time =========*/


        /*=========== Save event button listener ===========*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Event is created", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                // set the event to be finished here then resume parent activity

                String name = ((TextView) findViewById(R.id.event_name_add_event)).getText().toString();
                String location = ((TextView) findViewById(R.id.event_location_add_event)).getText().toString();
                boolean isStatic = false;
                boolean isPeriodic = false;
                boolean isFinished = false; // always set is Finished to False on Start
                String eventType = ((Spinner) findViewById(R.id.type_of_event_add_event)).getSelectedItem().toString();
                if (eventType.equals(items[0])) {
                    // This is static
                    isStatic = true;
                } else if (eventType.equals(items[1])) {
                    // This is dynamic
                    isStatic = false;
                } else {
                    Log.e("Error01", "EventType is none of the options");
                }
                String color = eventColorMap.get(((Spinner) findViewById(R.id.color_dropdown_add_event)).getSelectedItem().toString().trim());
                String notes = ((TextView) findViewById(R.id.notes_add_event)).getText().toString();


                Log.d("AddEventToList", name + "\n" + location + "\n" + eventType + "\n" + color + "\n" + notes);

                //setting start and ending time does not work, so we have dummy variable here

                String endDateText = "Start Date: " + ((startMonth == 0) ? 0 : (startMonth + 1)) + "/" + startDay + "/" + startYear +
                        " time: " + startHour + ":" + startMinute + "\n" +
                        " End Date" + ((endMonth == 0) ? 0 : (endMonth + 1)) + "/" + endDay + "/" + endYear +
                        " time: " + endHour + ":" + endMinute;

//                Toast.makeText(getApplicationContext(), endDateText, Toast.LENGTH_LONG).show();

                // Create Calendar start and end time
                Calendar startTime = Calendar.getInstance();
                startTime.set(startYear, startMonth, startDay, startHour, startMinute);
                Calendar endTime = Calendar.getInstance();
                endTime.set(endYear, endMonth, endDay, endHour, endMinute);

                boolean checkEventCreatedSuccessfully = false;

                // To create an event, we need to at least specify, event name, starting time and ending time
                // except hour and minute can be 0
                if (!name.equals("") && startYear != 0 && startMonth != 0 && startDay != 0
                        && endYear != 0 && endMonth != 0 && endDay != 0) {
                    try {
//                        DateFormat time = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//                        String event_time = time.format(startTime.getTime()) + "\n" +
//                                time.format(endTime.getTime());
//                        Log.d("eventTime", event_time);

                        if (isStatic) {
                            checkEventCreatedSuccessfully = EventListHandler.createStaticEvent(name, location, startTime, endTime,
                                    isStatic, isPeriodic, isFinished, notes, color);
                        } else {
                            // TODO - Create dyanmic event here

                        }

                    } catch (Exception e) {
                        Log.e("Error02", e.getMessage());
                    }


                    // If event is not created successfully
                    if (!checkEventCreatedSuccessfully) {
                        Snackbar.make(view, "Invalid time period. Start time must be earlier than end time.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    } else {
                        fab.hide();
                        // Wait 2 seconds, then resume parent activity (calendar view)
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // pass of the id of the clicked event to DetailActivity for loading event details
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }, 200);
                    }

                } else {
                    // If not having basic fields filled out, remaind the user
                    Snackbar.make(view, "Please fill out event name and time period.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

        /*=========== Save event button listener ===========*/

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


    /**
     * This is the start date date picker pop up in the add event page
     */
    public static class PickerDialogs extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DateSettings dateSettings = new DateSettings(getActivity());
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog;
            dialog = new DatePickerDialog(getActivity(), dateSettings, year, month, day);

            return dialog;
        }
    }


    /**
     * Thi is for start date, called by PickerDialogs to set the date text on button and
     */
    public static class DateSettings implements DatePickerDialog.OnDateSetListener {

        Context context;

        public DateSettings(Context context) {
            this.context = context;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            startYear = year;
            startMonth = monthOfYear;
            startDay = dayOfMonth;

            String startDateText = "Start Date: " + ((startMonth == 0) ? 0 : (startMonth + 1)) + "/" + dayOfMonth + "/" + year;
//            Toast.makeText(context, startDateText, Toast.LENGTH_LONG).show();

            // Reset the start date button text
            Button setStartDateButton = (Button) ((Activity) this.context).getWindow().getDecorView().findViewById(R.id.start_date_add_event);
            setStartDateButton.setText(startDateText);

        }
    }

    /**
     * This is the date picker pop up in the add event page
     */
    public static class PickerDialogs2 extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DateSettings2 dateSettings = new DateSettings2(getActivity());
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog;
            dialog = new DatePickerDialog(getActivity(), dateSettings, year, month, day);

            return dialog;
        }
    }


    public static class DateSettings2 implements DatePickerDialog.OnDateSetListener {

        Context context;

        public DateSettings2(Context context) {
            this.context = context;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            endYear = year;
            endMonth = monthOfYear;
            endDay = dayOfMonth;

            String endDateText = "End Date: " + ((endMonth == 0) ? 0 : (endMonth + 1)) + "/" + dayOfMonth + "/" + year;
//            Toast.makeText(context, endDateText, Toast.LENGTH_LONG).show();

            // Reset the end date button text
            Button setStartDateButton = (Button) ((Activity) this.context).getWindow().getDecorView().findViewById(R.id.end_date_add_event);
            setStartDateButton.setText(endDateText);
        }
    }

}
