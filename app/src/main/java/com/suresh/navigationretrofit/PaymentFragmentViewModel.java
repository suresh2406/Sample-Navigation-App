package com.suresh.navigationretrofit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PaymentFragmentViewModel extends ViewModel {
    private static final String TAG = PaymentFragmentViewModel.class.getSimpleName();
    MutableLiveData<Payments> mutablePaymentsData = new MutableLiveData<>();

    public void setPaymentData(Payments payment) {
        mutablePaymentsData.setValue(payment);
    }
}
