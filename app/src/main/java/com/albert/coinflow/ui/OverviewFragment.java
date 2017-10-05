package com.albert.coinflow.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.albert.coinflow.R;

import butterknife.Bind;

public class OverviewFragment extends Fragment {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    public OverviewFragment() {} // Empty constructor required for the fragment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        return rootView;
    }
}
