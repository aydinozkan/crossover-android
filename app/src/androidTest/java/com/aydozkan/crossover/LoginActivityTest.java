package com.aydozkan.crossover;

import android.support.test.rule.ActivityTestRule;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aydozkan.crossover.gui.activity.LoginActivity;
import com.aydozkan.crossover.utility.SharedPreferenceWrapper;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

public class LoginActivityTest extends ActivityTestRule<LoginActivity> {

    @Rule
    public final ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Test
    public void findViews() throws Exception {
        AutoCompleteTextView etEmail = (AutoCompleteTextView) mActivityRule.getActivity().findViewById(R.id.etEmail);
        EditText etPassword = (EditText) mActivityRule.getActivity().findViewById(R.id.etPassword);
        TextView tvNotMember = (TextView) mActivityRule.getActivity().findViewById(R.id.tvNotMember);
        Button btnLogin = (Button) mActivityRule.getActivity().findViewById(R.id.btnLogin);

        assertTrue(etEmail != null && etPassword != null && tvNotMember != null && btnLogin != null);
    }

    @Test
    public void isFormValid() throws Throwable {
        final AutoCompleteTextView etEmail = (AutoCompleteTextView) mActivityRule.getActivity().findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) mActivityRule.getActivity().findViewById(R.id.etPassword);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                etEmail.setText(mActivityRule.getActivity().getString(R.string.dummy_email_address));
                etPassword.setText(mActivityRule.getActivity().getString(R.string.dummy_password));

                assertTrue(mActivityRule.getActivity().isFormValid());
            }
        });
    }

    @Test
    public void storeAndCheckAccessToken() throws Exception {

        mActivityRule.getActivity().storeAccessToken("dummyAccessToken");

        assertTrue(SharedPreferenceWrapper.getInstance().getAccessToken().equals("dummyAccessToken"));
    }

    @Test
    public void loginTest_Success() throws Exception {
        // Type text and then press the button.
        onView(withId(R.id.etEmail))
                .perform(typeText("crossover@crossover.com"), closeSoftKeyboard());
        onView(withId(R.id.etPassword))
                .perform(typeText("crossover"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Check accessToken not empty
        assertTrue(SharedPreferenceWrapper.getInstance().getAccessToken() != null && !SharedPreferenceWrapper.getInstance().getAccessToken().isEmpty());
    }
}