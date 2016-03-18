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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.cse110.apk404.myCalendar.CalendarDB;
import com.cse110.apk404.myCalendar.CalendarError;
import com.cse110.apk404.myCalendar.CalendarEvent;
import com.cse110.apk404.myCalendar.CalendarObject;
import com.cse110.apk404.myCalendar.CalendarObjectList;
import com.cse110.apk404.myCalendar.EventListHandler;

import java.text.DateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Event detailed description that contains a delete button to delete this event
 * and a finished button to set the state of this event to be finished
 */
public class AddEventActivity extends AppCompatActivity {

    FloatingActionButton fab = null;
    CalendarEvent event = null;
    Toolbar toolbar = null;
    Button setStartTime = null;
    Button setEndTime = null;
    Button setStartDate = null;
    Button setEndDate = null;
    EditText dynamicEventDurationEditText = null;

    Calendar time = Calendar.getInstance();
    DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
    DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

    TimePickerDialog.OnTimeSetListener startTimePicker = null;
    TimePickerDialog.OnTimeSetListener endTimePicker = null;

    boolean isStatic = true;
    boolean isPeriodic = false;
    String eventType;

    final String defaultThemeColor = "#009688";
    final String[] colors = new String[]{"TEAL", "ORANGE", "PINK", "GREEN", "LIGHTGREEN", "BLUE", "PURPLE", "RED"};
    HashMap<String, String> eventColorMap = new HashMap<>();

    int dynamicEventDuration = 0;
    public static int START_YEAR, START_MONTH, START_DAY, START_HOUR, START_MINUTE = 0;
    public static int END_YEAR, END_MONTH, END_DAY, END_HOUR, END_MINUTE = 0;


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

        if (IS_EDIT_EVENT) event = EventListHandler.getEventById(ID);

        Log.d("AddEventIntentInfo", ID + "");
        Log.d("AddEventIntentInfo", IS_EDIT_EVENT + "");


        // Reset those static date int to 0 on start
        START_YEAR = START_MONTH = START_DAY = START_HOUR = START_MINUTE = 0;
        END_YEAR = END_MONTH = END_DAY = END_HOUR = END_MINUTE = 0;

        populateColorMap();

        /* Toolbar */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Enable back button in toolbar */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /* Floating action button */
        fab = (FloatingActionButton) findViewById(R.id.fab_finished_event);


        // Replace the home back button with delete button
        final Drawable closeIcon = getResources().getDrawable(R.drawable.ic_close_white_24dp);
        closeIcon.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(closeIcon);


        /*========= Time picker listeners and picker used to pick time =========*/

        setStartTime = (Button) findViewById(R.id.start_time_add_event);
        setEndTime = (Button) findViewById(R.id.end_time_add_event);

        startTimePicker = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setStartTime.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                START_HOUR = hourOfDay;
                START_MINUTE = minute;
            }
        };

        endTimePicker = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setEndTime.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                END_HOUR = hourOfDay;
                END_MINUTE = minute;
            }
        };

        /* Click on set start time button to make time picker pop up show up */
        setStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, startTimePicker, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true).show();
            }
        });

        /* Click on set end time button to make time picker pop up show up */
        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, endTimePicker, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true).show();
            }
        });

        /*==========================================================================*/


        setStartDate = (Button) findViewById(R.id.start_date_add_event);
        setEndDate = (Button) findViewById(R.id.end_date_add_event);


        // Set default theme color
        if (!IS_EDIT_EVENT) setToolbarStyle(defaultThemeColor, fab, toolbar);
        else setToolbarStyle(event.getColor(), fab, toolbar);

        dynamicEventDurationEditText = (EditText) findViewById(R.id.event_duration_add_event);

         /* Creates dropdown for type of event */
        Spinner dropdown = (Spinner) findViewById(R.id.type_of_event_add_event);
        final String[] items = new String[]{"STATIC", "DYNAMIC"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                eventType = ((Spinner) findViewById(R.id.type_of_event_add_event)).getSelectedItem().toString();
                if (eventType.equals(items[0])) {
                    isStatic = true;
                    setStartTime.setEnabled(true);
                    setStartTime.setVisibility(View.VISIBLE);
                    setStartDate.setEnabled(true);
                    setStartDate.setVisibility(View.VISIBLE);
                    dynamicEventDurationEditText.setEnabled(false);
                    dynamicEventDurationEditText.setVisibility(View.GONE);
                    if (IS_EDIT_EVENT) {
                        // If edit event page then do not reset the texts because there will be existing
                        // date show on the button
                    } else {
                        // If not editing event then reset the text
                        setEndTime.setText("Set End Time");
                        setEndDate.setText("Set End Date");
                    }

                } else if (eventType.equals(items[1])) {
                    isStatic = false;
                    setStartTime.setEnabled(false);
                    setStartTime.setVisibility(View.GONE);
                    setStartDate.setEnabled(false);
                    setStartDate.setVisibility(View.GONE);
                    dynamicEventDurationEditText.setEnabled(true);
                    dynamicEventDurationEditText.setVisibility(View.VISIBLE);
                    if (IS_EDIT_EVENT) {
                        // If edit event page then do not reset the texts because there will be existing
                        // date show on the button
                    } else {
                        // If not editing event then reset the text
                        setEndTime.setText("Set Deadline Time");
                        setEndDate.setText("Set Deadline Date");
                    }
                } else {
                    Log.e("Error01", "EventType is none of the options");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        /* Dropdown for color */
        Spinner colorPicker = (Spinner) findViewById(R.id.color_dropdown_add_event);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, colors);
        colorPicker.setAdapter(adapter2);
        // Set the color selection if is edit event
        if (IS_EDIT_EVENT) {
            String colorKey = "";
            for (Map.Entry<String, String> entry : eventColorMap.entrySet()) {
                if (entry.getValue().equals(event.getColor())) {
                    colorKey = entry.getKey();
                }
            }
            for (int i = 0; i < colors.length; i++) {
                if (colors[i].equals(colorKey)) {
                    colorPicker.setSelection(i);
                }
            }
        }
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



        /*========= If edit event, poulate fields with existing event info =========*/
        if (IS_EDIT_EVENT) {
            event = EventListHandler.getEventById(ID);
            if (event == null)
                Log.e("Error07", "Can't find event when editing event in add event page");

            setToolbarStyle(event.getColor(), fab, toolbar);

            ((EditText) findViewById(R.id.event_location_add_event)).setText(event.getLocation());
            ((EditText) findViewById(R.id.notes_add_event)).setText(event.getDescription());

            boolean eventIsStatic = event.isStatic();
            if (eventIsStatic) {
                ((TextView) findViewById(R.id.event_name_add_event)).setText(event.getName());

                Calendar startTme = event.getStartTime();
                Calendar endTime = event.getEndTime();

                ((Button) findViewById(R.id.start_date_add_event)).setText(dateFormatter.format(startTme.getTime()));
                ((Button) findViewById(R.id.start_time_add_event)).setText(timeFormatter.format(startTme.getTime()));
                ((Button) findViewById(R.id.end_date_add_event)).setText(dateFormatter.format(endTime.getTime()));
                ((Button) findViewById(R.id.end_time_add_event)).setText(timeFormatter.format(endTime.getTime()));

                START_HOUR = startTme.get(Calendar.HOUR_OF_DAY);
                START_MINUTE = startTme.get(Calendar.MINUTE);
                START_YEAR = startTme.get(Calendar.YEAR);
                START_MONTH = startTme.get(Calendar.MONTH);
                START_DAY = startTme.get(Calendar.DATE);
                END_HOUR = endTime.get(Calendar.HOUR_OF_DAY);
                END_MINUTE = endTime.get(Calendar.MINUTE);
                END_YEAR = endTime.get(Calendar.YEAR);
                END_MONTH = endTime.get(Calendar.MONTH);
                END_DAY = endTime.get(Calendar.DATE);
            } else {
                Log.d("editEvent", "this is dynamic.");

                // load the event name from saved event, cut of the dynamic prefix
                ((TextView) findViewById(R.id.event_name_add_event)).setText(event.getName().substring(10));

                int spinnerPosition = adapter.getPosition("DYNAMIC");
                dropdown.setSelection(spinnerPosition);

                setStartTime.setEnabled(false);
                setStartTime.setVisibility(View.GONE);
                setStartDate.setEnabled(false);
                setStartDate.setVisibility(View.GONE);
                dynamicEventDurationEditText.setEnabled(true);
                dynamicEventDurationEditText.setVisibility(View.VISIBLE);
                dynamicEventDurationEditText.setText("" + (((DynamicEvent)event).getEstimatedLength() / 60));

                Calendar deadLineTime = ((DynamicEvent)event).getDeadline();

                ((Button) findViewById(R.id.end_date_add_event)).setText(dateFormatter.format(deadLineTime.getTime()));
                ((Button) findViewById(R.id.end_time_add_event)).setText(timeFormatter.format(deadLineTime.getTime()));

                END_HOUR = deadLineTime.get(Calendar.HOUR_OF_DAY);
                END_MINUTE = deadLineTime.get(Calendar.MINUTE);
                END_YEAR = deadLineTime.get(Calendar.YEAR);
                END_MONTH = deadLineTime.get(Calendar.MONTH);
                END_DAY = deadLineTime.get(Calendar.DATE);
            }
        }
        /*==========================================================================*/


        /*=============== Save event button listener ===============*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // set the event to be finished here then resume parent activity
                String name = ((EditText) findViewById(R.id.event_name_add_event)).getText().toString();
                String location = ((EditText) findViewById(R.id.event_location_add_event)).getText().toString();
                eventType = ((Spinner) findViewById(R.id.type_of_event_add_event)).getSelectedItem().toString();
                if (eventType.equals(items[0])) isStatic = true;
                else if (eventType.equals(items[1])) isStatic = false;
                else Log.e("Error01", "EventType is none of the options");
                String color = eventColorMap.get(((Spinner) findViewById(R.id.color_dropdown_add_event)).getSelectedItem().toString().trim());
                String notes = ((TextView) findViewById(R.id.notes_add_event)).getText().toString();

                if (!isStatic) {
                    String duration= ((EditText) findViewById(R.id.event_duration_add_event)).getText().toString().trim();
                    if (duration.length() == 0) {
                        dynamicEventDuration = 0;
                    } else {
                        dynamicEventDuration = Integer.parseInt(duration);
                    }
                }

                Log.d("AddEventToList", name + "\n" + location + "\n" + eventType + "\n" + color + "\n" + notes);

                //setting start and ending time does not work, so we have dummy variable here

//                String endDateText = "Start Date: " + ((START_MONTH == 0) ? 0 : (START_MONTH + 1)) + "/" + START_DAY + "/" + START_YEAR +
//                        " time: " + START_HOUR + ":" + START_MINUTE + "\n" +
//                        " End Date" + ((END_MONTH == 0) ? 0 : (END_MONTH + 1)) + "/" + END_DAY + "/" + END_YEAR +
//                        " time: " + END_HOUR + ":" + END_MINUTE;
//
//                Toast.makeText(getApplicationContext(), endDateText, Toast.LENGTH_LONG).show();


                boolean checkEventCreatedSuccessfully = false;

                // To create an event, we need to at least specify, event name, starting time and ending time
                // except hour and minute can be 0
                if (!name.equals("") && ((START_YEAR != 0 && START_MONTH != 0 && START_DAY != 0 && isStatic) || (dynamicEventDuration != 0 && !isStatic))
                        && (END_YEAR != 0 && END_MONTH != 0 && END_DAY != 0)) {

                    // Create Calendar start and end time
                    Calendar startTime = Calendar.getInstance();
                    startTime.set(START_YEAR, START_MONTH, START_DAY, START_HOUR, START_MINUTE);
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(END_YEAR, END_MONTH, END_DAY, END_HOUR, END_MINUTE);

                    try {
//                        DateFormat time = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//                        String event_time = time.format(startTime.getTime()) + "\n" +
//                                time.format(endTime.getTime());
//                        Log.d("eventTime", event_time);

                        // If we are editing the event we create a new one and delete the old one
                        if (IS_EDIT_EVENT) EventListHandler.removeEventById(ID);

                        if (isStatic) {
                            checkEventCreatedSuccessfully = EventListHandler.createStaticEvent(name, location, startTime, endTime,true, isPeriodic, false, notes, color);
                        } else {
                            int estimatedLengthInMinutes = dynamicEventDuration * 60;
                            checkEventCreatedSuccessfully = EventListHandler.createDynamicEvent("[Dynamic] "+name, false, location,notes, color, endTime, estimatedLengthInMinutes, false);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
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
                    Snackbar.make(view, "Please have valid times, event name and time period.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
        /*==========================================================================*/

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
            START_YEAR = year;
            START_MONTH = monthOfYear;
            START_DAY = dayOfMonth;

            String startDateText = ((START_MONTH == 0) ? 0 : (START_MONTH + 1)) + "/" + dayOfMonth + "/" + year;
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
            END_YEAR = year;
            END_MONTH = monthOfYear;
            END_DAY = dayOfMonth;

            String endDateText = ((END_MONTH == 0) ? 0 : (END_MONTH + 1)) + "/" + dayOfMonth + "/" + year;
//            Toast.makeText(context, endDateText, Toast.LENGTH_LONG).show();

            // Reset the end date button text
            Button setStartDateButton = (Button) ((Activity) this.context).getWindow().getDecorView().findViewById(R.id.end_date_add_event);
            setStartDateButton.setText(endDateText);
        }
    }

}
