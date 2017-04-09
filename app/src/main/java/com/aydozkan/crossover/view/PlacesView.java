package com.aydozkan.crossover.view;

import com.aydozkan.crossover.network.models.pojo.Place;

import java.util.List;

/**
 * Routes Map data to the User Interface, which will be displayed afterwards.
 * PlacesView is being implemented by PlacesActivity
 */
public interface PlacesView extends BaseView {
    /**
     * Successful GetPlaces Service Callback
     *
     * @param placeList GetPlaces service response
     */
    void onGetPlaces(List<Place> placeList);

    /**
     * Successful RentPayment Service Callback
     *
     * @param message RentPayment service response
     */
    void onRentPayment(String message);
}
