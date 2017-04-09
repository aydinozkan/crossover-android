package com.aydozkan.crossover;

import com.aydozkan.crossover.network.models.pojo.Place;
import com.aydozkan.crossover.network.models.response.AuthenticateUserResponse;
import com.aydozkan.crossover.network.models.response.GetPlacesResponse;
import com.aydozkan.crossover.network.models.response.RentPaymentResponse;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class ResponseUnitTest {

    @Test
    public void authenticateUserResponseGetters_Success() {
        AuthenticateUserResponse authenticateUserResponse = new AuthenticateUserResponse("accessToken");

        assertTrue(authenticateUserResponse.getAccessToken() != null && !authenticateUserResponse.getAccessToken().isEmpty());
    }

    @Test
    public void getPlacesResponseGetters_Success() {
        GetPlacesResponse getPlacesResponse = new GetPlacesResponse(new ArrayList<Place>());

        assertTrue(getPlacesResponse.getPlaceList() != null);
    }

    @Test
    public void rentPaymentsResponseGetters_Success() {
        RentPaymentResponse rentPaymentResponse = new RentPaymentResponse("message");

        assertTrue(rentPaymentResponse.getMessage() != null && !rentPaymentResponse.getMessage().isEmpty());
    }
}