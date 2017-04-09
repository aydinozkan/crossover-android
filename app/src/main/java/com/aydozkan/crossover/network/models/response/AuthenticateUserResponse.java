package com.aydozkan.crossover.network.models.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Response Class for AuthenticateUser Service Call
 */
public class AuthenticateUserResponse implements Serializable {
    @SerializedName("accessToken")
    private final String mAccessToken;

    public AuthenticateUserResponse(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getAccessToken() {
        return mAccessToken;
    }
}
