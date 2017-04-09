package com.aydozkan.crossover.network.models.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Location POJO
 */
public class Location implements Serializable {

    @SerializedName("lat")
    private final double mLatitude;

    @SerializedName("lng")
    private final double mLongitude;

    public Location(double latitude, double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }
}
