package com.aydozkan.crossover;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.DialogFragment;

import com.aydozkan.crossover.gui.activity.PlacesActivity;
import com.aydozkan.crossover.gui.fragment.PaymentFragmentDialog;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class PaymentTest {

    @Rule
    public final ActivityTestRule<PlacesActivity> mActivityRule = new ActivityTestRule<>(
            PlacesActivity.class);

    @Test
    public void paymentTestTextChange_Success() throws Exception {

        DialogFragment paymentDialogFragment = PaymentFragmentDialog.newInstance("testTitle");
        paymentDialogFragment.show(mActivityRule.getActivity().getSupportFragmentManager(), mActivityRule.getActivity().getString(R.string.payment_dialog));

        // Type text and then press the button.
        onView(withId(R.id.bt_card_form_cvv))
                .perform(typeText("testCvv"), closeSoftKeyboard());

        onView(withId(R.id.bt_card_form_postal_code))
                .perform(typeText("testPostalCode"), closeSoftKeyboard());

        onView(withId(R.id.btnRent)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.bt_card_form_cvv))
                .check(matches(withText("testCvv")));

        // Check that the text was changed.
        onView(withId(R.id.bt_card_form_postal_code))
                .check(matches(withText("testPostalCode")));
    }
}