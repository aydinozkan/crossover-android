package com.aydozkan.crossover.network.models.response;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Response Class for RentPayment Service Call
 */
public class RentPaymentResponse implements Serializable {
    @SerializedName("message")
    private final String mMessage;

    public RentPaymentResponse(String message) {
        mMessage = message;
    }

    @Nullable
    public String getMessage() {
        return mMessage;
    }
}
