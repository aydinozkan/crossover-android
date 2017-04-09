package com.aydozkan.crossover.presenter;

import android.support.annotation.NonNull;

import com.aydozkan.crossover.network.http.ResponseCallback;
import com.aydozkan.crossover.network.http.RetrofitClient;
import com.aydozkan.crossover.network.models.request.AuthenticateUserRequest;
import com.aydozkan.crossover.network.models.response.AuthenticateUserResponse;
import com.aydozkan.crossover.network.service.AuthenticationService;
import com.aydozkan.crossover.view.AuthenticationView;

import retrofit2.Response;

/**
 * Mediates AuthenticationView and Authentication Models.
 */
public class AuthenticationPresenter {

    private final AuthenticationService mAuthenticationService;
    private final AuthenticationView mView;

    public AuthenticationPresenter(AuthenticationView view) {
        mAuthenticationService = RetrofitClient.getInstance().getRetrofit().create(AuthenticationService.class);
        mView = view;
    }

    /**
     * Enqueues authenticateUser Service Call and mediates AuthenticationView.
     * Authenticates an existing User.
     *
     * @param email    emailAddress value coming from LoginActivity
     * @param password password value coming from LoginActivity
     */
    public void authenticateUser(String email, String password) {

        AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
        authenticateUserRequest.setEmail(email);
        authenticateUserRequest.setPassword(password);

        mView.onBeforeRequest();

        mAuthenticationService.authenticateUser(authenticateUserRequest).enqueue(new ResponseCallback<AuthenticateUserResponse>() {
            @Override
            public void onResponse(@NonNull Response<AuthenticateUserResponse> response) {
                mView.onAfterRequest();
                mView.onUserAuthentication(response.body().getAccessToken());
            }

            @Override
            public void onFailure(String errorMessage) {
                mView.onAfterRequest();
                mView.onError(errorMessage);
            }
        });
    }

    /**
     * Enqueues registerUser Service Call and mediates AuthenticationView.
     * Registers a new User.
     *
     * @param email    emailAddress value coming from LoginActivity
     * @param password password value coming from LoginActivity
     */
    public void registerUser(String email, String password) {

        AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
        authenticateUserRequest.setEmail(email);
        authenticateUserRequest.setPassword(password);

        mView.onBeforeRequest();

        mAuthenticationService.registerUser(authenticateUserRequest).enqueue(new ResponseCallback<AuthenticateUserResponse>() {
            @Override
            public void onResponse(@NonNull Response<AuthenticateUserResponse> response) {
                mView.onAfterRequest();
                mView.onUserRegistration(response.body().getAccessToken());
            }

            @Override
            public void onFailure(String errorMessage) {
                mView.onAfterRequest();
                mView.onError(errorMessage);
            }
        });
    }
}
