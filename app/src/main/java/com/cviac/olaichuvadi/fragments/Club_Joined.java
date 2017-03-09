package com.cviac.olaichuvadi.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.Club_OwnAdapter;
import com.cviac.olaichuvadi.datamodels.Club_recInfo;

import java.util.ArrayList;
import java.util.List;

public class Club_Joined extends Fragment {

    GridView gv;
    List<Club_recInfo> club;
    Club_OwnAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_club__recommended, container, false);

        loadclubs();

        gv = (GridView) view.findViewById(R.id.readingclub_rec);
        adapter = new Club_OwnAdapter(getActivity().getApplicationContext(), club);
        gv.setAdapter(adapter);
        return view;
    }

    private void loadclubs() {
        club = new ArrayList<>();

        Club_recInfo c1 = new Club_recInfo("Club-1");
        club.add(c1);
        Club_recInfo c2 = new Club_recInfo("Club-2");
        club.add(c2);
        Club_recInfo c3 = new Club_recInfo("Club-3");
        club.add(c3);
        Club_recInfo c4 = new Club_recInfo("Club-4");
        club.add(c4);
        Club_recInfo c5 = new Club_recInfo("Club-5");
        club.add(c5);
        Club_recInfo c6 = new Club_recInfo("Club-6");
        club.add(c6);
        Club_recInfo c7 = new Club_recInfo("Club-7");
        club.add(c7);
        Club_recInfo c8 = new Club_recInfo("Club-8");
        club.add(c8);
    }
}