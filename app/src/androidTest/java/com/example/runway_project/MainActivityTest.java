package com.example.runway_project;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenario = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void firstTryAtInstrumentalTesting(){
        onView(withText("Log In")).check(matches(isDisplayed()));
        onView(withText("Log In")).check(isCompletelyAbove(withId(R.id.email)));
    }

    @Test
    public void locationOfLogoTest(){
        onView(withId(R.id.logo)).check(matches(isDisplayed()));
        onView(withId(R.id.logo)).check(isCompletelyAbove(withText("Log In")));
    }

    @Test
    public void locationOfEditTexts(){
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(isCompletelyBelow(withText("Log In")));
        onView(withId(R.id.password)).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(isCompletelyBelow(withId(R.id.email)));
    }

    @Test
    public void locationOfButton(){
        onView(withId(R.id.loginBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.loginBtn)).check(isCompletelyBelow(withId(R.id.password)));
    }

    @Test
    public void locationOfRegister(){
        onView(withId(R.id.regTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.regTxt)).check(isCompletelyBelow(withId(R.id.loginBtn)));
    }

    @Test
    public void ButtonClicks(){
        onView(withId(R.id.loginBtn)).check(matches(isClickable()));
//        onView(withId(R.id.loginBtn)).perform(click());
    }


    @Test
    public void RegClicks(){
        onView(withId(R.id.regTxt)).check(matches(isClickable()));
    }

    @Test
    public void whenRegClicks(){
        onView(withId(R.id.regTxt)).perform(click());
    }
}
