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
public class Reg2Test {
    @Rule
    public ActivityScenarioRule<Register_2> activityScenario = new ActivityScenarioRule<Register_2>(Register_2.class);

    @Test
    public void locationOfLogoTest(){
        onView(withId(R.id.logo)).check(matches(isDisplayed()));
        onView(withId(R.id.logo)).check(isCompletelyAbove(withId(R.id.register_2_Txt)));
    }

    @Test
    public void RegTextLocation(){
        onView(withId(R.id.register_2_Txt)).check(matches(isDisplayed()));
        onView(withId(R.id.register_2_Txt)).check(isCompletelyAbove(withId(R.id.reg2ExpTxt)));
    }

    @Test
    public void locationOfEnterDetsText(){
        onView(withId(R.id.reg2ExpTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.reg2ExpTxt)).check(isCompletelyAbove(withId(R.id.regEmail)));
    }

    @Test
    public void emlEditTextTest(){
        onView(withId(R.id.regEmail)).check(matches(isDisplayed()));
        onView(withId(R.id.regEmail)).check(matches(withHint("Email")));
    }

    @Test
    public void EmailTextVisibilityButtonLocation(){
        onView(withId(R.id.showEml)).check(matches(isDisplayed()));
        onView(withId(R.id.showEml)).check(isCompletelyRightOf(withId(R.id.regEmail)));
    }

    @Test
    public void showEmlClickable(){
        onView(withId(R.id.showEml)).check(matches(isClickable()));
    }

    @Test
    public void showEmlClicks(){
        onView(withId(R.id.showEml)).perform(click());
        onView(withId(R.id.showEml)).check(matches(not(isDisplayed())));
        onView(withId(R.id.hideEml)).check(matches(isDisplayed()));
        onView(withId(R.id.hideEml)).check(matches(isClickable()));
        onView(withId(R.id.hideEml)).perform(click());
        onView(withId(R.id.showEml)).check(matches(isDisplayed()));
    }

    @Test
    public void locationOfPasswordDetsText(){
        onView(withId(R.id.pwdExpTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.pwdExpTxt)).check(isCompletelyBelow(withId(R.id.regEmail)));
    }

    @Test
    public void pwdEditTextTest(){
        onView(withId(R.id.regPwd)).check(matches(isDisplayed()));
        onView(withId(R.id.regPwd)).check(matches(withHint("Password")));
    }

    @Test
    public void pwdTextVisibilityButtonLocation(){
        onView(withId(R.id.showPwd)).check(matches(isDisplayed()));
        onView(withId(R.id.showPwd)).check(isCompletelyRightOf(withId(R.id.regPwd)));
    }

    @Test
    public void showPwdClickable(){
        onView(withId(R.id.showPwd)).check(matches(isClickable()));
    }

    @Test
    public void showPwdClicks(){
        onView(withId(R.id.showPwd)).perform(click());
        onView(withId(R.id.showPwd)).check(matches(not(isDisplayed())));
        onView(withId(R.id.hidePwd)).check(matches(isDisplayed()));
        onView(withId(R.id.hidePwd)).check(matches(isClickable()));
        onView(withId(R.id.hidePwd)).perform(click());
        onView(withId(R.id.showPwd)).check(matches(isDisplayed()));
    }

    @Test
    public void pwdCEditTextTest(){
        onView(withId(R.id.regReEntPwd)).check(matches(isDisplayed()));
        onView(withId(R.id.regReEntPwd)).check(matches(withHint("Re-enter Password")));
    }

    @Test
    public void pwdCTextVisibilityButtonLocation(){
        onView(withId(R.id.showPwdC)).check(matches(isDisplayed()));
        onView(withId(R.id.showPwdC)).check(isCompletelyRightOf(withId(R.id.regReEntPwd)));
    }

    @Test
    public void showPwdCClickable(){
        onView(withId(R.id.showPwdC)).check(matches(isClickable()));
    }

    @Test
    public void showPwdCClicks(){
        onView(withId(R.id.showPwdC)).perform(click());
        onView(withId(R.id.showPwdC)).check(matches(not(isDisplayed())));
        onView(withId(R.id.hidePwdC)).check(matches(isDisplayed()));
        onView(withId(R.id.hidePwdC)).check(matches(isClickable()));
        onView(withId(R.id.hidePwdC)).perform(click());
        onView(withId(R.id.showPwdC)).check(matches(isDisplayed()));
    }

    @Test
    public void locationOfButton(){
        onView(withId(R.id.reg2Btn)).check(matches(isDisplayed()));
        onView(withId(R.id.reg2Btn)).check(isCompletelyBelow(withId(R.id.regReEntPwd)));
    }

    @Test
    public void vouchBtnClick(){
        onView(withId(R.id.reg2Btn)).check(matches(isClickable()));
    }

}




