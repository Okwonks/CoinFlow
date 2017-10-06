package com.albert.coinflow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.albert.coinflow.R;
import com.albert.coinflow.models.Budget;

public class FirebaseOverviewViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;

    public FirebaseOverviewViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindOverviewBudget(Budget budget) {
        TextView totalTextView = mView.findViewById(R.id.totalMoneyTextView);
        totalTextView.setText(budget.getTotal());
    }
}
