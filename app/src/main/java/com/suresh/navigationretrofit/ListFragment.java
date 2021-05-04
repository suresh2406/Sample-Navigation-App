package com.suresh.navigationretrofit;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suresh.navigationretrofit.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements PaymentsAdapter.PaymentSelectListener {
    private static final String TAG = ListFragment.class.getSimpleName();
    private NavController mNavigationController;
    private FragmentListBinding mListBinding;
    private ListFragmentViewModel mListViewModel;
    private PaymentsAdapter mPaymentsAdapter;
    public static final String SELECTED_PAYMENT = "SELECTED_PAYMENT";
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mListViewModel = new ViewModelProvider(getActivity()).get(ListFragmentViewModel.class);
        mListViewModel.fetchPayments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        if(view == null){
            Log.d(TAG,"view is null");
            mListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false);
            mNavigationController = Navigation.findNavController(getActivity(), R.id.fragment);
            view = mListBinding.getRoot();
            setPaymentsAdapter(new ArrayList<>());
            initObservers();
        }
        return view;
    }

    private void initObservers() {
        mListViewModel.mutablePaymentsList.observe(getViewLifecycleOwner(), new Observer<List<Payments>>() {
            @Override
            public void onChanged(List<Payments> payments) {
                mListViewModel.hideLoadingIndicator();
                mPaymentsAdapter.updateList(payments);
            }
        });
        mListViewModel.getShouldShowLoadingIndicator().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean show) {
                mListBinding.progressHorizontal.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setPaymentsAdapter(List<Payments> payments) {
        mPaymentsAdapter = new PaymentsAdapter(payments, this);
        mListBinding.rvList.setAdapter(mPaymentsAdapter);
        mListBinding.rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListBinding.rvList.setHasFixedSize(true);
    }

    @Override
    public void onPaymentSelected(Payments payment) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_PAYMENT, payment);
        mNavigationController.navigate(R.id.action_listFragment_to_itemFragment, bundle);
    }
}