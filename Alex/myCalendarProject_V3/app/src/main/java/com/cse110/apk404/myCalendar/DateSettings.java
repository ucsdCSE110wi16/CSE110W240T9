package com.cse110.apk404.myCalendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;
/**
 * Created by will.jiang on 2/24/16.
 */
public class DateSettings implements DatePickerDialog.OnDateSetListener{

    Context context;
    public DateSettings(Context context)
    {
        this.context = context;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(context,"Selected date :"+dayOfMonth+ " / "+monthOfYear+" / "+year,Toast.LENGTH_LONG).show();

    }

}
