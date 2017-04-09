package com.aydozkan.crossover;

import com.aydozkan.crossover.network.http.RetrofitClient;
import com.aydozkan.crossover.network.models.response.GetPlacesResponse;
import com.aydozkan.crossover.network.service.PlacesService;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

public class GetPlaceListUnitTest {

    @Test
    public void getPlacesWithoutAccessToken() {
        PlacesService mPlacesService = RetrofitClient.getInstance().getRetrofit().create(PlacesService.class);

        Call<GetPlacesResponse> call = mPlacesService.getPlaces();

        try {
            Response<GetPlacesResponse> response = call.execute();

            GetPlacesResponse getPlacesResponse = response.body();

            assertTrue(getPlacesResponse == null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}