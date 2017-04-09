package com.aydozkan.crossover.network.models.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Place POJO
 */
public class Place implements Serializable {

    @SerializedName("location")
    private final Location mLocation;

    @SerializedName("id")
    private final String mId;

    @SerializedName("name")
    private final String mName;

    public Place(Location location, String id, String name) {
        mLocation = location;
        mId = id;
        mName = name;
    }

    public Location getLocation() {
        return mLocation;
    }

    public String getName() {
        return mName;
    }
}
