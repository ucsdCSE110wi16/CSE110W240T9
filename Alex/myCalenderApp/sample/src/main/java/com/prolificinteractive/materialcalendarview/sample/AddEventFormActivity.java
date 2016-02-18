package com.prolificinteractive.materialcalendarview.sample;

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

/**
 * This is the event form for specifying properties for events
 */
public class AddEventFormActivity extends AppCompatActivity {

    private Button addEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_form);
        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        String[] items = new String[]{"Static", "Periodic"};
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
    }
}

