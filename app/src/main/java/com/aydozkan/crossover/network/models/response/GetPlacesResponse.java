package com.aydozkan.crossover.network.models.response;

import android.support.annotation.Nullable;

import com.aydozkan.crossover.network.models.pojo.Place;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Response Class for GetPlaces Service Call
 */
public class GetPlacesResponse implements Serializable {
    @SerializedName("results")
    private final List<Place> mPlaceList;

    public GetPlacesResponse(List<Place> placeList) {
        mPlaceList = placeList;
    }

    @Nullable
    public final List<Place> getPlaceList() {
        return mPlaceList;
    }
}