package com.albert.coinflow.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.coinflow.Constants;
import com.albert.coinflow.R;
import com.albert.coinflow.adapters.FirebaseBudgetListAdapter;
import com.albert.coinflow.adapters.FirebaseBudgetViewHolder;
import com.albert.coinflow.models.Budget;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OverviewFragment extends Fragment {
    private DatabaseReference mBudgetReference;
    private FirebaseBudgetListAdapter mFirebaseAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    public OverviewFragment() {} // Empty constructor required for the fragment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, view);

        setUpFirebaseAdapter();
        return view;
    }

    public void setUpFirebaseAdapter() {
        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_BUDGET);
//                .child(uid);

        mFirebaseAdapter = new FirebaseBudgetListAdapter(Budget.class, R.layout.daily_expenses_list, FirebaseBudgetViewHolder.class, query, getActivity());

        /* Adding the data to the recycler view */
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
