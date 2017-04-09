package com.aydozkan.crossover.view;

/**
 * Routes accessToken to the User Interface, which will be used afterwards.
 * AuthenticationView is being implemented by LoginActivity
 */
public interface AuthenticationView extends BaseView {
    void onUserAuthentication(String accessToken);
    void onUserRegistration(String accessToken);
}