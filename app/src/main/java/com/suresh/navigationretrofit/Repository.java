package com.suresh.navigationretrofit;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    private static Repository mInstance;

    public static Repository getInstance() {
        if (mInstance == null) {
            mInstance = new Repository();
        }
        return mInstance;
    }

    public void fetchPayments(PaymentUser paymentUser, MutableLiveData<List<Payments>> mutablePaymentsList) {
        Retrofit retrofit = APIClient.getClient();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<ArrayList<Payments>> userListCall = apiInterface.getPaymentsList(paymentUser);
        userListCall.enqueue(new Callback<ArrayList<Payments>>() {
            @Override
            public void onResponse(Call<ArrayList<Payments>> call, Response<ArrayList<Payments>> response) {
                Log.d(TAG, "response : " + response.body().toString());
                mutablePaymentsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Payments>> call, Throwable t) {

            }
        });
    }
}
