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
