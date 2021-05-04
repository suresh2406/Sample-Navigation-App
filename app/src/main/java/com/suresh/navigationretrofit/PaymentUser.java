package com.suresh.navigationretrofit;

import com.google.gson.annotations.SerializedName;

public class PaymentUser {
    @SerializedName("customerID")
    private String userId;

    public PaymentUser(String userId){
        this.userId = userId;
    }
}
