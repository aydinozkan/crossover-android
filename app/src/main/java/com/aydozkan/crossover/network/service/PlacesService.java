package com.aydozkan.crossover.network.service;

import android.support.annotation.NonNull;

import com.aydozkan.crossover.network.models.request.RentPaymentRequest;
import com.aydozkan.crossover.network.models.response.GetPlacesResponse;
import com.aydozkan.crossover.network.models.response.RentPaymentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Contains Places related GET and POST Interface methods.
 */
public interface PlacesService {

    /**
     * Get Places Get Method
     *
     * @return AuthenticateUserResponse
     */
    @NonNull
    @GET("/api/v1/places")
    Call<GetPlacesResponse> getPlaces();

    /**
     * Rent Payment Post Method
     *
     * @param request RentPaymentRequest
     * @return RentPaymentResponse
     */
    @NonNull
    @POST("/api/v1/rent")
    Call<RentPaymentResponse> rentPayment(@Body RentPaymentRequest request);
}