package com.prolificinteractive.materialcalendarview.sample.decorators;


/**
 * Decorate a day by making the text big and bold
 */
public class OneDayDecorator implements DayViewDecorator {

    private CalendarDay date;

    public OneDayDecorator() {
        
    }

    @Override
    public boolean shouldDecorate(CalendarDay myDay) {
        
    }

    @Override
    public void decorate(DayViewFacade myView) {
      
    }

    /**
     * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
     */
    public void setDate(Date newDate) {
       
    }
}
