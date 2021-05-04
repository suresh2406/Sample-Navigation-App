package com.suresh.navigationretrofit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.suresh.navigationretrofit.databinding.FragmentPaymentBinding;

public class PaymentFragment extends Fragment {
    private static final String TAG = PaymentFragment.class.getSimpleName();
    private PaymentFragmentViewModel mViewModel;
    private FragmentPaymentBinding mBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PaymentFragmentViewModel.class);
        initObservers();
        Bundle bundle = getArguments();
        if (bundle != null) {
            Payments payment = (Payments) bundle.getSerializable(ListFragment.SELECTED_PAYMENT);
            mViewModel.setPaymentData(payment);
        }
    }

    private void initObservers() {
        mViewModel.mutablePaymentsData.observe(getViewLifecycleOwner(), new Observer<Payments>() {
            @Override
            public void onChanged(Payments payments) {
                mBinding.setPayment(payments);
            }
        });
    }
}