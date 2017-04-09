package com.aydozkan.crossover.network.service;

import android.support.annotation.NonNull;

import com.aydozkan.crossover.network.models.request.AuthenticateUserRequest;
import com.aydozkan.crossover.network.models.response.AuthenticateUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Contains Authentication related GET and POST Interface methods.
 */
public interface AuthenticationService {

    /**
     * Authenticate User Post Method.
     *
     * @param request AuthenticateUserRequest
     * @return AuthenticateUserResponse
     */
    @NonNull
    @POST("/api/v1/auth")
    Call<AuthenticateUserResponse> authenticateUser(@Body AuthenticateUserRequest request);

    /**
     * Register new User Post Method.
     *
     * @param request AuthenticateUserRequest
     * @return AuthenticateUserResponse
     */
    @NonNull
    @POST("/api/v1/register")
    Call<AuthenticateUserResponse> registerUser(@Body AuthenticateUserRequest request);
}
