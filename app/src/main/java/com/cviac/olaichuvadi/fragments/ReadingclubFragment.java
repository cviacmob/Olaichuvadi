package com.cviac.olaichuvadi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.ReadingclubAdapter;
import com.cviac.olaichuvadi.datamodels.ReadingclubInfo;

import java.util.ArrayList;
import java.util.List;

public class ReadingclubFragment extends Fragment {

    GridView gv;
    List<ReadingclubInfo> read_club;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_readingclub, container, false);

        readclub();

        ReadingclubAdapter adapter = new ReadingclubAdapter(getActivity(), read_club);
        gv = (GridView) view.findViewById(R.id.read_grid);
        gv.setAdapter(adapter);
        return view;
    }

    private void readclub() {

        read_club = new ArrayList<>();

        ReadingclubInfo rc1 = new ReadingclubInfo("Tamil Readers", R.mipmap.ic);
        read_club.add(rc1);

        ReadingclubInfo rc2 = new ReadingclubInfo("Chronicles", R.mipmap.ic);
        read_club.add(rc2);

        ReadingclubInfo rc4 = new ReadingclubInfo("Crime", R.mipmap.ic);
        read_club.add(rc4);

        ReadingclubInfo rc5 = new ReadingclubInfo("Comics", R.mipmap.ic);
        read_club.add(rc5);

        ReadingclubInfo rc6 = new ReadingclubInfo("Biography", R.mipmap.ic);
        read_club.add(rc6);

        ReadingclubInfo rc7 = new ReadingclubInfo("Auto Biography", R.mipmap.ic);
        read_club.add(rc7);

    }
}
