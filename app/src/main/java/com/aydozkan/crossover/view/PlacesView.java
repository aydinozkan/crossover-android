package com.aydozkan.crossover.view;

import com.aydozkan.crossover.network.models.pojo.Place;

import java.util.List;

/**
 * Routes Map data to the User Interface, which will be displayed afterwards.
 * PlacesView is being implemented by PlacesActivity
 */
public interface PlacesView extends BaseView {
    void onGetPlaces(List<Place> placeList);
    void onRentPayment(String message);
}