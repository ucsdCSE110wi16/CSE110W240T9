package com.cse110.apk404.myCalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.cse110.apk404.myCalendar.R;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by will.jiang on 2/24/16.
 */
public class AddEventActivity extends AppCompatActivity{

    private Button done;

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

    }
}
