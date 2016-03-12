package com.cse110.apk404.myCalendar.AndrroidTest;

/******************************************
 * Created by Kristinektran on 3/11/16.
 ******************************************/

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.cse110.apk404.myCalendar.DetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.PositionAssertions.isRightOf;
import static android.support.test.espresso.assertion.PositionAssertions.isTopAlignedWith;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Sriven on 12/5/2015.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetailEventTest
{
    //Instantiate ActivityTestRule - > with add_event
    @Rule
    public ActivityTestRule<DetailActivity> detailActivityRule = new ActivityTestRule(DetailActivity.class);



    //TEST if "Event Name " is displayed
    @Test
    public void testEventDisplay() {
        onView(withText("Event Name"))
                .check(matches(isDisplayed()));
    }

    //TEST if "Event Time" is displayed
    @Test
    public void testEventTime() {
        onView(withText("Event Time"))
                .check(matches(isDisplayed()));
    }

    //TEST if "Event Location" is displayed
    @Test
    public void testLocationDisplay()
    {
        onView(withText("Event Location"))
                .check(matches(isDisplayed()));
    }


    //TEST if "This is a description of the event." is displayed
    @Test
    public void testLocationDisplay()
    {
        onView(withText("This is a description of the event."))
                .check(matches(isDisplayed()));
    }

    //TEST fail by ID
    @Test
    public void testException()
    {
        onView(withId(12345))
                .check(doesNotExist());
    }
}

