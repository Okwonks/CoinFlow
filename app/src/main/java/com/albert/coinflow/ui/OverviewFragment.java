package com.albert.coinflow.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.albert.coinflow.Constants;
import com.albert.coinflow.R;
import com.albert.coinflow.adapters.FirebaseBudgetListAdapter;
import com.albert.coinflow.adapters.FirebaseBudgetViewHolder;
import com.albert.coinflow.adapters.FirebaseOverviewViewHolder;
import com.albert.coinflow.adapters.OverviewFirebaseAdapter;
import com.albert.coinflow.models.Budget;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OverviewFragment extends Fragment {
    private DatabaseReference mBudgetReference;
    private OverviewFirebaseAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.welcomeTextView) TextView mWelcomeTextView;

    public OverviewFragment() {} // Empty constructor required for the fragment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, view);

        setUpFirebaseAdapter();
        return view;
    }

    public void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();
            mWelcomeTextView.setVisibility(View.INVISIBLE);
            Query query = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_BUDGET)
                    .child(uid);

            mFirebaseAdapter = new OverviewFirebaseAdapter(Budget.class, R.layout.overview_detail_list, FirebaseOverviewViewHolder.class, query, getActivity());

        /* Adding the data to the recycler view */
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mFirebaseAdapter);
        } else {
            // This else statement stops the app from crashing due to lack of logged in user.
            mWelcomeTextView.setVisibility(View.VISIBLE);
        }
    }
}
