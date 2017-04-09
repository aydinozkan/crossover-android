package com.aydozkan.crossover.view;

/**
 * Routes accessToken to the User Interface, which will be used afterwards.
 * AuthenticationView is being implemented by LoginActivity
 */
public interface AuthenticationView extends BaseView {
    /**
     * User Authentication Callback.
     *
     * @param accessToken accessToken returned from authentication Service
     */
    void onUserAuthentication(String accessToken);

    /**
     * User Registration Callback.
     *
     * @param accessToken returned from authentication Service
     */
    void onUserRegistration(String accessToken);
}
