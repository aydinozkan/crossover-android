package com.aydozkan.crossover;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.aydozkan.crossover.gui.activity.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeTextBehaviorTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);
    private String mEmailAddress, mPassword;

    @Before
    public void initializeValidString() {
        // Specify a valid string.
        mEmailAddress = "dummy@example.com";
        mPassword = "q1w2e3r4";
    }

    @Test
    public void changeEmailText() {
        // Type text and then press the button.
        onView(withId(R.id.etEmail))
                .perform(typeText(mEmailAddress), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.etEmail))
                .check(matches(withText(mEmailAddress)));
    }

    @Test
    public void changePasswordText() {
        // Type text and then press the button.
        onView(withId(R.id.etPassword))
                .perform(typeText(mPassword), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.etPassword))
                .check(matches(withText(mPassword)));
    }
}