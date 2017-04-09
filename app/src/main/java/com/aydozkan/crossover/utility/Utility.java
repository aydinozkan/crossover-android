package com.aydozkan.crossover.utility;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains common Utility Methods
 */
public final class Utility {
    private static final String TAG = Utility.class.getName();
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    private Utility() {
    }

    /**
     * Checks whether the given email address is valid according to mail standards or not.
     *
     * @param email Given email address
     * @return email validity
     */
    public static boolean isValidEmail(String email) {
        try {
            Pattern p = Pattern.compile(EMAIL_PATTERN);
            Matcher m = p.matcher(email);
            return m.matches();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }
}
