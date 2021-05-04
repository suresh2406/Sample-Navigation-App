package com.suresh.navigationretrofit;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.suresh.navigationretrofit.databinding.PaymentItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder> {
    private List<Payments> mPaymentsList;
    private PaymentSelectListener mPaymentSelectListner;


    public PaymentsAdapter(List<Payments> payments,PaymentSelectListener listener) {
        mPaymentsList = payments;
        mPaymentSelectListner = listener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PaymentItemBinding paymentItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.payment_item, parent, false);
        return new PaymentViewHolder(paymentItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        boolean shouldshowDate = true;
        Payments currentpayment = mPaymentsList.get(position);
        if (position > 0) {
            Payments previousPayment = mPaymentsList.get(position - 1);
            if (TextUtils.equals(currentpayment.getCompletedDateTime(), previousPayment.getCompletedDateTime())) {
                shouldshowDate = false;
            }
        }
        holder.populateView(currentpayment, shouldshowDate);
    }

    @Override
    public int getItemCount() {
        return mPaymentsList.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {
        private final PaymentItemBinding mItemBinding;

        public PaymentViewHolder(PaymentItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mItemBinding = itemBinding;
        }

        public void populateView(Payments payment, boolean shouldShowDate) {
            mItemBinding.setPayment(payment);
            mItemBinding.setDateVisibility(shouldShowDate);
            mItemBinding.setPaymentItemClickListener(mPaymentSelectListner);
        }
    }

    public void updateList(List<Payments> payments){
        DiffUtil.DiffResult diffUtil = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return mPaymentsList.size();
            }

            @Override
            public int getNewListSize() {
                return payments.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return mPaymentsList.get(oldItemPosition).equals(payments.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return mPaymentsList.get(oldItemPosition).equals(payments.get(newItemPosition));
            }
        });
        mPaymentsList.clear();
        mPaymentsList.addAll(payments);
        diffUtil.dispatchUpdatesTo(this);
    }

    public interface PaymentSelectListener{
        void onPaymentSelected(Payments payment);
    }
}
