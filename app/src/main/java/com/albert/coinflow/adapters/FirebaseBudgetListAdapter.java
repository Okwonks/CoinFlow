package com.albert.coinflow.adapters;

import android.content.Context;

import com.albert.coinflow.models.Budget;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FirebaseBudgetListAdapter extends FirebaseRecyclerAdapter<Budget, FirebaseBudgetViewHolder> {
    private DatabaseReference mRef;
    private Context mContext;
    private ArrayList<Budget> mBudgets = new ArrayList<>();

    public FirebaseBudgetListAdapter(Class<Budget> modelClass, int modelLayout, Class<FirebaseBudgetViewHolder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseBudgetViewHolder viewHolder, Budget model, int position) {
        viewHolder.bindBudget(model);
    }
}
