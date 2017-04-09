package com.aydozkan.crossover.network.models.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Request Class for Authenticate User Service Call
 */
public class AuthenticateUserRequest implements Serializable {
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
