package com.cviac.olaichuvadi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;

public class Shared_Available_Fragment extends Fragment {

    GridView gv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__shared_avail, container, false);

        gv = (GridView) view.findViewById(R.id.shared_avail);
        return view;
    }
}