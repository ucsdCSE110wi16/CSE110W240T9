package com.cse110.apk404.myCalendar;

/**
 * Created by Kristinektran on 3/18/16.
 */


import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest
{
    private String emailLogin;
    private String passWordLogin;
    //Instantiate ActivityTestRule - > with add_event
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string for event name, location and notes
        emailLogin = "team404pk@ucsd.edu";
        passWordLogin = "KesdenIsAwesome";

    }

    @Test
    public void testCreateEvent () throws InterruptedException
    {
        /*******************************************************************************************************
         *                     TEST3 : LOGIN ACTIVITY TEST
         *******************************************************************************************************/
        // Test for sync calendar username sign in
        onView(withId(R.id.email)).perform(typeText(emailLogin), ViewActions.closeSoftKeyboard());
        // Wait function
        synchronized (this) {
            Thread.sleep((long) 1000);
        }


        // Test for sync calendar username sign in
        onView(withId(R.id.password)).perform(typeText(passWordLogin), ViewActions.closeSoftKeyboard());
        // Wait function
        synchronized (this) {
            Thread.sleep((long) 1000);
        }

        // Test for sync calendar passwords sign in
        onView(withId(R.id.email_sign_in_button)).perform(click());
        synchronized (this) {
            Thread.sleep((long) 10000);
        }
    }
}

