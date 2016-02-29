package com.cse110.apk404.myCalendar;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cse110.apk404.myCalendar.eventListHandler.CalendarDate;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;


/**
 * Event detailed description that contains a delete button to delete this event
 * and a finished button to set the state of this event to be finished
 */
public class AddEventActivity extends AppCompatActivity {

    private Toolbar toolbar = null;
    private Button setStartTime = null;
    private Button setEndTime = null;
    DateFormat fmtTime = DateFormat.getDateTimeInstance();
    Calendar time = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datePicker = null;
    TimePickerDialog.OnTimeSetListener timePicker = null;

    //For calculations
    public int startYear, startMonth, startDay, startHour, startMinute, startDayOfWeek;
    public int endYear, endMonth, endDay, endHour, endMinute, endDayOfWeek;
    public String startMonthString, endMonthString;


    HashMap<String, String> eventColorMap = new HashMap<>();

    public void populateColorMap() {
        eventColorMap.put("Red", "#F44336");
        eventColorMap.put("Orange", "#FF5722");
        eventColorMap.put("Pink", "#E91E63");
        eventColorMap.put("Green", "#4CAF50");
        eventColorMap.put("LightGreen", "#F44336");
        eventColorMap.put("Blue", "#2196F3");
        eventColorMap.put("Purple", "#9C27B0");
        eventColorMap.put("Teal", "#009688");
    }

    /*the setDate funtion*/
    public void setStartDate(View view) {
        PickerDialogs pickerDialogs = new PickerDialogs();
        pickerDialogs.show(getSupportFragmentManager(), "date_picker");
    }

    /*the setDate funtion*/
    public void setEndDate(View view) {
        PickerDialogs pickerDialogs = new PickerDialogs();
        pickerDialogs.show(getSupportFragmentManager(), "date_picker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

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
        final String[] items = new String[]{"Static-NonPeriodic", "Static-Periodic", "Dynamic"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        /* Dropdown for color */
        Spinner colorPicker = (Spinner) findViewById(R.id.color_dropdown_add_event);
        final String[] colors = new String[]{"Red", "Orange", "Pink", "Green", "LightGreen", "Blue", "Purple", "Teal"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, colors);
        colorPicker.setAdapter(adapter2);

        /* Used to pick time */
        timePicker = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                time.set(Calendar.HOUR_OF_DAY, hourOfDay);
                time.set(Calendar.MINUTE, minute);
            }
        };

        setStartTime = (Button) findViewById(R.id.start_time_add_event);

        setStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, timePicker,
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE), true).show();
                Context context = getApplicationContext();
                Toast.makeText(context, "Selected time - " + Calendar.HOUR_OF_DAY + " : " + Calendar.MINUTE, Toast.LENGTH_LONG).show();
                startHour = Calendar.HOUR_OF_DAY;
                startMinute = Calendar.MINUTE;
            }
        });

        setEndTime = (Button) findViewById(R.id.end_time_add_event);

        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, timePicker,
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE), true).show();
                Context context = getApplicationContext();
                Toast.makeText(context, "Selected time :" + Calendar.HOUR_OF_DAY + ":" + Calendar.MINUTE, Toast.LENGTH_LONG).show();
                endHour = Calendar.HOUR_OF_DAY;
                endMinute = Calendar.MINUTE;
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Event is created", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                fab.hide();

                // TODO - set the event to be finished here then resume parent activity


                //public boolean createStaticEvent(
                // String name,
                // String location,
                // CalendarDate startTime,
                // CalendarDate endTime,
                // boolean isStatic,
                // boolean isPeriodic,
                // boolean isFinished,
                // String description,
                // String color)

                String name = ((TextView) findViewById(R.id.event_name_add_event)).getText().toString();
                String location = ((TextView) findViewById(R.id.event_location_add_event)).getText().toString();
                boolean isStatic = false;
                boolean isPeriodic = false;
                boolean isFinished = false; // always set is Finished to False on Start
                String eventType = ((Spinner) findViewById(R.id.type_of_event_add_event)).getSelectedItem().toString();
                if (eventType.equals(items[0])) {
                    // This is static non periodic
                    isStatic = true;
                } else if (eventType.equals(items[1])) {
                    // This is static periodic
                    isStatic = true;
                    isPeriodic = true;
                } else if (eventType.equals(items[2])) {
                    // This is dynamic
                } else {
                    Log.e("Error", "EventType is none of the options");
                }
                String color = ((Spinner) findViewById(R.id.color_dropdown_add_event)).getSelectedItem().toString();
                Calendar startTime = new CalendarDate(startYear, startMonth, startDay, startHour,
                        startMinute, startDayOfWeek, startMonthString);
                Calendar endTime = new CalendarDate(endYear, endMonth, endDay, endHour,
                        endMinute, endDayOfWeek, endMonthString);


                String notes = ((TextView) findViewById(R.id.notes_add_event)).getText().toString();

                Log.d("AddEvent", name + "\n" + location + "\n" + eventType + "\n" + color + "\n" + notes);
                // Wait 2 seconds, then create the event and resume parent activity (calendar view)
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 400);
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
     * This is the date picker pop up in the add event page
     */
    public class PickerDialogs extends DialogFragment {

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


    public class DateSettings implements DatePickerDialog.OnDateSetListener {

        Context context;

        public DateSettings(Context context) {
            this.context = context;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear++;
            Toast.makeText(context, "Selected date :" + monthOfYear + " / " + dayOfMonth + " / " + year, Toast.LENGTH_LONG).show();
            startYear = year;
            startMonth = monthOfYear;
            startDay = dayOfMonth;
            startDayOfWeek = Calendar.DAY_OF_WEEK;

            switch (monthOfYear) {
                case 1:  startMonthString = "Jan";
                    break;
                case 2:  startMonthString = "Feb";
                    break;
                case 3:  startMonthString = "Mar";
                    break;
                case 4:  startMonthString = "Apr";
                    break;
                case 5:  startMonthString = "May";
                    break;
                case 6:  startMonthString = "Jun";
                    break;
                case 7:  startMonthString = "Jul";
                    break;
                case 8:  startMonthString = "Aug";
                    break;
                case 9:  startMonthString = "Sep";
                    break;
                case 10: startMonthString = "Oct";
                    break;
                case 11: startMonthString = "Nov";
                    break;
                case 12: startMonthString = "Dec";
                    break;
            }
        }
    }

    /**
     * This is the date picker pop up in the add event page
     */
    public class PickerDialogs2 extends DialogFragment {

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


    public class DateSettings2 implements DatePickerDialog.OnDateSetListener {

        Context context;

        public DateSettings(Context context) {
            this.context = context;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear++;
            Toast.makeText(context, "Selected date :" + monthOfYear + " / " + dayOfMonth + " / " + year, Toast.LENGTH_LONG).show();
            endYear = year;
            endMonth = monthOfYear;
            endDay = dayOfMonth;
            endDayOfWeek = Calendar.DAY_OF_WEEK;

            switch (monthOfYear) {
                case 1:
                    endMonthString = "Jan";
                    break;
                case 2:
                    endMonthString = "Feb";
                    break;
                case 3:
                    endMonthString = "Mar";
                    break;
                case 4:
                    endMonthString = "Apr";
                    break;
                case 5:
                    endMonthString = "May";
                    break;
                case 6:
                    endMonthString = "Jun";
                    break;
                case 7:
                    endMonthString = "Jul";
                    break;
                case 8:
                    endMonthString = "Aug";
                    break;
                case 9:
                    endMonthString = "Sep";
                    break;
                case 10:
                    endMonthString = "Oct";
                    break;
                case 11:
                    endMonthString = "Nov";
                    break;
                case 12:
                    endMonthString = "Dec";
                    break;
            }
        }
    }
}
