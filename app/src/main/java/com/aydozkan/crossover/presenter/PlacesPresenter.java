package com.aydozkan.crossover.presenter;

import android.support.annotation.NonNull;

import com.aydozkan.crossover.network.http.ResponseCallback;
import com.aydozkan.crossover.network.http.RetrofitClient;
import com.aydozkan.crossover.network.models.request.RentPaymentRequest;
import com.aydozkan.crossover.network.models.response.GetPlacesResponse;
import com.aydozkan.crossover.network.models.response.RentPaymentResponse;
import com.aydozkan.crossover.network.service.PlacesService;
import com.aydozkan.crossover.view.PlacesView;

import retrofit2.Response;

/**
 * Mediates PlacesView and Place Models
 */
public class PlacesPresenter {

    private final PlacesService mPlacesService;
    private final PlacesView mView;

    public PlacesPresenter(PlacesView view) {
        mPlacesService = RetrofitClient.getInstance().getRetrofit().create(PlacesService.class);
        mView = view;
    }

    /**
     * Enqueues getPlaces and mediates PlacesView onGetPlaces CallBack
     */
    public void getPlaces() {

        mView.onBeforeRequest();

        mPlacesService.getPlaces().enqueue(new ResponseCallback<GetPlacesResponse>() {
            @Override
            public void onResponse(@NonNull Response<GetPlacesResponse> response) {
                mView.onAfterRequest();
                mView.onGetPlaces(response.body().getPlaceList());
            }

            @Override
            public void onFailure(String errorMessage) {
                mView.onAfterRequest();
                mView.onError(errorMessage);
            }
        });
    }

    /**
     * Enqueues rentPayment and mediates PlacesView onRentPayment Callback
     *
     * @param cardNumber     Number of Credit Card
     * @param cardOwnerName  Owner Name of Credit Card
     * @param expirationDate Expiration Date of Credit Card (MM/YYYY)
     * @param securityCode   Security Code of Credit Card
     */
    public void rentPayment(String cardNumber, String cardOwnerName, String expirationDate, String securityCode) {

        RentPaymentRequest rentPaymentRequest = new RentPaymentRequest();

        rentPaymentRequest.setCardNumber(cardNumber);
        rentPaymentRequest.setCardOwnerName(cardOwnerName);
        rentPaymentRequest.setExpirationDate(expirationDate);
        rentPaymentRequest.setSecurityCode(securityCode);

        mView.onBeforeRequest();

        mPlacesService.rentPayment(rentPaymentRequest).enqueue(new ResponseCallback<RentPaymentResponse>() {
            @Override
            public void onResponse(@NonNull Response<RentPaymentResponse> response) {
                mView.onAfterRequest();
                mView.onRentPayment(response.body().getMessage());
            }

            @Override
            public void onFailure(String errorMessage) {
                mView.onAfterRequest();
                mView.onError(errorMessage);
            }
        });
    }
}
