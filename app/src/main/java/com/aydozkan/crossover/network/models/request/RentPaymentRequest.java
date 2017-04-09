package com.aydozkan.crossover.network.models.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Request Class for Rent Payment Service Call
 */
public class RentPaymentRequest implements Serializable {
    @SerializedName("number")
    private String mCardNumber;
    @SerializedName("name")
    private String mCardOwnerName;
    @SerializedName("expiration")
    private String mExpirationDate;
    @SerializedName("code")
    private String mSecurityCode;

    public void setCardNumber(String cardNumber) {
        mCardNumber = cardNumber;
    }

    public void setCardOwnerName(String cardOwnerName) {
        mCardOwnerName = cardOwnerName;
    }

    public void setExpirationDate(String expirationDate) {
        mExpirationDate = expirationDate;
    }

    public void setSecurityCode(String securityCode) {
        mSecurityCode = securityCode;
    }
}
