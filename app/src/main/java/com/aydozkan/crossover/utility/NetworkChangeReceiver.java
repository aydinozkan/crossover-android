package com.aydozkan.crossover.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Listens to Connectivity Change and fires a ConnectivityChangeEvent
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null) {
            Log.d("NetworkChangeReceiver", "YES");
            EventBus.getDefault().post(new ConnectivityChangeEvent(netInfo.getState()));
        } else {
            Log.d("NetworkChangeReceiver", "NO");
            EventBus.getDefault().post(new ConnectivityChangeEvent(NetworkInfo.State.DISCONNECTED));
        }
    }
}


