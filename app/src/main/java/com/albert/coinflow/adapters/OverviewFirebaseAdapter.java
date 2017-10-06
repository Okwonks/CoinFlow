package com.albert.coinflow.adapters;

import android.content.Context;

import com.albert.coinflow.models.Budget;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class OverviewFirebaseAdapter extends FirebaseRecyclerAdapter<Budget, FirebaseOverviewViewHolder> {
    private DatabaseReference mRef;
    private Context mContext;


    public OverviewFirebaseAdapter(Class<Budget> modelClass, int modelLayout, Class<FirebaseOverviewViewHolder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseOverviewViewHolder viewHolder, Budget model, int position) {
        viewHolder.bindOverviewBudget(model);
    }
}
