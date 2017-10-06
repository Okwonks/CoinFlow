package com.albert.coinflow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.albert.coinflow.R;
import com.albert.coinflow.models.Budget;

public class FirebaseBudgetViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;

    public FirebaseBudgetViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindBudget(Budget budget) {
        TextView dateTextView = mView.findViewById(R.id.dateTextView);
        TextView expensesTextView = mView.findViewById(R.id.expensesTextView);
        TextView incomeTextView = mView.findViewById(R.id.incomeTextView);
//        TextView totalTextView = mView.findViewById(R.id.totalTextView);
        // Set values
        dateTextView.setText(budget.getDate());
        expensesTextView.setText(budget.getExpenses());
        incomeTextView.setText(budget.getIncome());
//        totalTextView.setText(budget.getTotal());
    }
}
