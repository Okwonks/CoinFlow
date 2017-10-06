package com.albert.coinflow.adapters;

import android.content.Context;

import com.albert.coinflow.models.Budget;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FirebaseBudgetListAdapter extends FirebaseRecyclerAdapter<Budget, FirebaseBudgetViewHolder> {
    private DatabaseReference mRef;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Budget> mBudgets = new ArrayList<>();

    public FirebaseBudgetListAdapter(Class<Budget> modelClass, int modelLayout, Class<FirebaseBudgetViewHolder> viewHolderClass, Query ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mContext = context;

//        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                mBudgets.add(dataSnapshot.getValue(Budget.class));
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    protected void populateViewHolder(FirebaseBudgetViewHolder viewHolder, Budget model, int position) {
        viewHolder.bindBudget(model);
    }

//    @Override
//    public void cleanup() {
//        super.cleanup();
//        mRef.removeEventListener(mChildEventListener);
//    }
}
