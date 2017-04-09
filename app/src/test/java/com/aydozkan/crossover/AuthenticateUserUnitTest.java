package com.aydozkan.crossover;

import com.aydozkan.crossover.network.http.RetrofitClient;
import com.aydozkan.crossover.network.models.request.AuthenticateUserRequest;
import com.aydozkan.crossover.network.models.response.AuthenticateUserResponse;
import com.aydozkan.crossover.network.service.AuthenticationService;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

public class AuthenticateUserUnitTest {

    @Test
    public void authenticateUser() {
        AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
        authenticateUserRequest.setEmail("crossover@crossover.com");
        authenticateUserRequest.setPassword("crossover");

        AuthenticationService authenticationService = RetrofitClient.getInstance().getRetrofit().create(AuthenticationService.class);

        Call<AuthenticateUserResponse> call = authenticationService.authenticateUser(authenticateUserRequest);

        try {
            Response<AuthenticateUserResponse> response = call.execute();

            AuthenticateUserResponse authenticateUserResponse = response.body();

            assertTrue(authenticateUserResponse != null && authenticateUserResponse.getAccessToken() != null && !authenticateUserResponse.getAccessToken().isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerUser() {
        AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
        authenticateUserRequest.setEmail("dummy@example.com");
        authenticateUserRequest.setPassword("q1w2e3r4");

        AuthenticationService authenticationService = RetrofitClient.getInstance().getRetrofit().create(AuthenticationService.class);

        Call<AuthenticateUserResponse> call = authenticationService.registerUser(authenticateUserRequest);

        try {
            Response<AuthenticateUserResponse> response = call.execute();

            AuthenticateUserResponse authenticateUserResponse = response.body();

            assertTrue(authenticateUserResponse != null && authenticateUserResponse.getAccessToken() != null && !authenticateUserResponse.getAccessToken().isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}