package com.example.runway_project;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.PositionAssertions.isBottomAlignedWith;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HomeTest {


    @Rule
    public ActivityScenarioRule<Home> activityScenario = new ActivityScenarioRule<Home>(Home.class);

    @Test
    public void welcomeTxtLocation(){
        onView(withText("Welcome to Runway")).check(matches(isDisplayed()));
        onView(withText("Welcome to Runway")).check(isCompletelyAbove(withId(R.id.runwayInfo)));
    }

    @Test
    public void runwayInfoLocation(){
        onView(withId(R.id.runwayInfo)).check(matches(isDisplayed()));
    }

    @Test
    public void emergLogoutBtn(){
        onView(withId(R.id.logoutImgBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.logoutImgBtn)).check(matches(isClickable()));
        onView(withId(R.id.logoutImgBtn)).perform(click());
    }


}
