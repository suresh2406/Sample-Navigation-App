package com.suresh.navigationretrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("paymentsHistory")
    Call<ArrayList<Payments>> getPaymentsList(@Body PaymentUser paymentUser);
}
