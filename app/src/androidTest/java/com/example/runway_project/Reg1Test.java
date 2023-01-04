package com.example.runway_project;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyRightOf;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.not;

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
public class Reg1Test {
    @Rule
    public ActivityScenarioRule<Register_1> activityScenario = new ActivityScenarioRule<>(Register_1.class);

    @Test
    public void locationOfLogoTest(){
        onView(withId(R.id.logo)).check(matches(isDisplayed()));
        onView(withId(R.id.logo)).check(isCompletelyAbove(withText("Register")));
    }

    @Test
    public void RegTextLocation(){
        onView(withText("Register")).check(matches(isDisplayed()));
        onView(withText("Register")).check(isCompletelyAbove(withId(R.id.regExpTxt)));
    }

    @Test
    public void locationOfText(){
        onView(withId(R.id.regExpTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.regExpTxt)).check(isCompletelyAbove(withId(R.id.voucherNo)));
    }

    @Test
    public void EditTextTest(){
        onView(withId(R.id.voucherNo)).check(matches(isDisplayed()));
        onView(withId(R.id.voucherNo)).check(matches(withHint("Voucher No.")));
    }

    @Test
    public void TextVisibilityButtonLocation(){
        onView(withId(R.id.showBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.showBtn)).check(isCompletelyRightOf(withId(R.id.voucherNo)));
    }

    @Test
    public void showBtnClickable(){
        onView(withId(R.id.showBtn)).check(matches(isClickable()));
    }

    @Test
    public void showBtnClicks(){
        onView(withId(R.id.showBtn)).perform(click());
        onView(withId(R.id.showBtn)).check(matches(not(isDisplayed())));
        onView(withId(R.id.hideBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.hideBtn)).check(matches(isClickable()));
        onView(withId(R.id.hideBtn)).perform(click());
        onView(withId(R.id.showBtn)).check(matches(isDisplayed()));
    }


    @Test
    public void locationOfButton(){
        onView(withId(R.id.vouchBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.vouchBtn)).check(isCompletelyBelow(withId(R.id.voucherNo)));
    }

    @Test
    public void vouchBtnClick(){
        onView(withId(R.id.vouchBtn)).check(matches(isClickable()));
    }

}
