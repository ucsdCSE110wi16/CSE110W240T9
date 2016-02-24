package com.prolificinteractive.materialcalendarview.sample.decorators;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.sample.R;

public class MySelectorDecorator implements DayViewDecorator {

    private final Drawable myDrawable;

    public MySelectorDecorator(Activity myActivity) {
        myDrawable = myActivity.getResources().getDrawable(R.myDrawable.my_selector);
    }

    @Override
    public boolean shouldDecorate(CalendarDay myDay) {
        return true;
    }

    @Override
    public void decorate(DayViewFacade myDayView) {
        myDayView.setSelectionDrawable(myDrawable);
    }

}
