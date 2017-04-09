package com.aydozkan.crossover.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.aydozkan.crossover.CrossOverApplication;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Keeps a reference to the SharedPreference
 * Acts as a Singleton class
 */
public class SharedPreferenceWrapper {

    private static final String TAG = SharedPreferenceWrapper.class.getName();
    private static final String SHARED_PREFERENCES_NAME = "crossOver";
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final SharedPreferenceWrapper ourInstance = new SharedPreferenceWrapper();
    private SharedPreferences mSharedPreferences;

    private SharedPreferenceWrapper() {
    }

    /**
     * Returns ourInstance of SharedPreferenceWrapper
     *
     * @return SharedPreferenceWrapper instance
     */
    public static SharedPreferenceWrapper getInstance() {
        return ourInstance;
    }

    /**
     * Initialize the SharedPreferenceWrapper class to keep a reference to the SharedPreference for this
     * application the SharedPreference will use the package name of the application as the Key.
     *
     * @param context Application context.
     */
    public void initialize(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Returns decrypted accessToken stored in SharedPreferences Instance if exists, else returns an empty string
     *
     * @return accessToken
     */
    public String getAccessToken() {
        if (mSharedPreferences != null)
            try {
                return CrossOverApplication.getInstance().getCrypt().decrypt(mSharedPreferences.getString(KEY_ACCESS_TOKEN, ""));
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidAlgorithmParameterException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
                Log.e(TAG, e.getMessage());
            }

        return "";
    }

    /**
     * Stores the given accessToken into SharedPreferences Instance
     *
     * @param accessToken given accessToken
     */
    public void setAccessToken(String accessToken) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.apply();
    }
}
