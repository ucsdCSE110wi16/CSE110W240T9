package com.prolificinteractive.materialcalendarview.sample;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


/**
 * This is the event form for specifying properties for events
 */
public class AddEventFormActivity extends AppCompatActivity {

    private Button addEvent;
    private TimePicker timePicker;
    private TextView textView;
    private Calendar calendar;
    private String ampm = "";


    /*the setDate funtion*/
    public void setDate(View view)
    {
        PickerDialogs pickerDialogs = new PickerDialogs();
        pickerDialogs.show(getSupportFragmentManager(),"date_picker");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);
        Spinner dropdown = (Spinner) findViewById(R.id.typeOfEvent);
        String[] items = new String[]{"Dynamic", "Static"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


                /* This is the add event button */
        addEvent = (Button) findViewById(R.id.btnDoneAddEvent);
        addEvent.setText("Done");
//        addEvent.setBackgroundResource(R.drawable.ic_add_event);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEventFormActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /* This sets up the time picker */
        timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(false);
        calendar = Calendar.getInstance();

//        //calendar returns it in 24hr format so we must convert
//        int currHour = calendar.get(Calendar.HOUR_OF_DAY);
//        int currMinute = calendar.get(Calendar.MINUTE);
//        displayTime(currHour, currMinute);
    }

//    public void displayTime(int currHour, int currMinute) {
//        if (currHour == 0) { //edge cases for setting current time
//            currHour = 12;
//            ampm = "AM";
//        } else if (currHour == 12) {
//            ampm = "PM";
//        } else if (currHour > 12) {
//            currHour = currHour - 12;
//            ampm = "PM";
//        } else {
//            ampm = "AM";
//        }
//        textView.setText(new StringBuffer().append(currHour).append(":").append(currMinute)
//                .append(" ").append(ampm));
//    }
//
//    public void newTime(View view){
//        int currHour = calendar.get(Calendar.HOUR_OF_DAY);
//        int currMinute = calendar.get(Calendar.MINUTE);
//        displayTime(currHour, currMinute);
//    }
}

