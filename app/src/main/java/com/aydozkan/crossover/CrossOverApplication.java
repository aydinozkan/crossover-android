package com.aydozkan.crossover;

import android.app.Application;

import com.aydozkan.crossover.utility.SharedPreferenceWrapper;

import se.simbio.encryption.Encryption;

/**
 * CrossOverApplication
 */
public class CrossOverApplication extends Application {

    private static final String KEY = "2Y5PTM925LCY6034T67320525YL291B0";
    private static final String SALT = "6k30rnc2t0m2rp";
    private static final Encryption ENCRYPTION = Encryption.getDefault(KEY, SALT, new byte[16]);
    private static CrossOverApplication sInstance;

    public static synchronized CrossOverApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        SharedPreferenceWrapper.getInstance().initialize(this);
    }

    public Encryption getCrypt() {
        return ENCRYPTION;
    }
}
