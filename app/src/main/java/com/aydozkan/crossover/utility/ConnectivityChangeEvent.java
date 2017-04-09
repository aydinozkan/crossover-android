package com.aydozkan.crossover.utility;

import android.net.NetworkInfo;

/**
 * Event used to determine Connectivity State
 *
 * @see NetworkChangeReceiver
 */
public class ConnectivityChangeEvent {
    public final NetworkInfo.State state;

    public ConnectivityChangeEvent(NetworkInfo.State state) {
        this.state = state;
    }
}