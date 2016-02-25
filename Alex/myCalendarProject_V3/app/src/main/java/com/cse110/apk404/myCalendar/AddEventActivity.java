package com.cse110.apk404.myCalendar;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.app.Activity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;
import com.cse110.apk404.myCalendar.R;


/**
 * Created by will.jiang on 2/24/16.
 */
public class AddEventActivity extends AppCompatActivity{

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
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);

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

        /* This is the done button */
        done = (Button) findViewById(R.id.btnDoneAddEvent);
        done.setText("Done");
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        setStart=(Button)findViewById(R.id.startTime);

        setStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEventActivity.this, timePicker,
                        time.get(Calendar.HOUR_OF_DAY),
                        time.get(Calendar.MINUTE), true).show();
            }
        });

//        timeLabel=(TextView)findViewById(R.id.time);
//        timeLabel.setText(fmtTime.format(time.getTime()));

    }
}
