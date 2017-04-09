package com.aydozkan.crossover.utility;

import android.net.NetworkInfo;

/**
 * Event used to determine Connectivity State.
 *
 * @see NetworkChangeReceiver
 */
public class ConnectivityChangeEvent {
    private final NetworkInfo.State mState;

    public ConnectivityChangeEvent(NetworkInfo.State state) {
        this.mState = state;
    }

    public NetworkInfo.State getState() {
        return mState;
    }
}
