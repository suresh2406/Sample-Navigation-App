package com.suresh.navigationretrofit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ListFragmentViewModel extends ViewModel {
    private static final String TAG = ListFragmentViewModel.class.getSimpleName();
    MutableLiveData<List<Payments>> mutablePaymentsList = new MutableLiveData<>();
    private MutableLiveData<Boolean> mShouldShowLoadingIndicator = new MutableLiveData<>(false);

    public MutableLiveData<Boolean> getShouldShowLoadingIndicator() {
        return mShouldShowLoadingIndicator;
    }

    private void showLoadingIndicator() {
        setVisibilityOfLoadingIndicator(true);
    }

    public void hideLoadingIndicator() {
        setVisibilityOfLoadingIndicator(false);
    }

    private void setVisibilityOfLoadingIndicator(boolean shouldShow) {
        mShouldShowLoadingIndicator.setValue(shouldShow);
    }

    public void fetchPayments() {
        PaymentUser paymentUser = new PaymentUser("KC1855");
        Repository.getInstance().fetchPayments(paymentUser, mutablePaymentsList);
        showLoadingIndicator();
    }


}
