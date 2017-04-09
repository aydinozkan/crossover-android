package com.aydozkan.crossover;

import android.app.Application;

import com.aydozkan.crossover.utility.SharedPreferenceWrapper;

import se.simbio.encryption.Encryption;

public class CrossOverApplication extends Application {

    private static final String key = "2Y5PTM925LCY6034T67320525YL291B0";
    private static final String salt = "6k30rnc2t0m2rp";

    private static CrossOverApplication _instance;

    private static final Encryption sEncryption = Encryption.getDefault(key, salt, new byte[16]);

    public synchronized static CrossOverApplication getInstance() {
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = this;

        SharedPreferenceWrapper.getInstance().initialize(this);
    }

    public Encryption getCrypt(){
        return sEncryption;
    }
}
