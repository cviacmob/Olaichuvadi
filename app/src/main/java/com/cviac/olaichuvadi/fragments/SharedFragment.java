package com.cviac.olaichuvadi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.SharedAdapter;
import com.cviac.olaichuvadi.datamodels.SharedInfo;

import java.util.ArrayList;
import java.util.List;

public class SharedFragment extends Fragment {

    GridView gv;
    List<SharedInfo> shrditms;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_shared, container, false);

        shrd_item();

        SharedAdapter adapter = new SharedAdapter(getActivity(), shrditms);
        gv = (GridView) view.findViewById(R.id.shrdgrd);
        gv.setAdapter(adapter);
        return view;
    }

    private void shrd_item() {

        shrditms = new ArrayList<>();

        SharedInfo sh1 = new SharedInfo("3 Mistakes of My Life", R.mipmap.ic, "Gunaa", "$ 50");
        shrditms.add(sh1);

        SharedInfo sh2 = new SharedInfo("Twenty 20", R.mipmap.ic, "Indu", "$ 70");
        shrditms.add(sh2);

        SharedInfo sh3 = new SharedInfo("Man of Steel", R.mipmap.ic, "Ganesh", "$ 64");
        shrditms.add(sh3);

        SharedInfo sh4 = new SharedInfo("Harry Potter", R.mipmap.ic, "Priya", "$ 58");
        shrditms.add(sh4);

        SharedInfo sh5 = new SharedInfo("Lootera", R.mipmap.ic, "Arun", "$ 100");
        shrditms.add(sh5);

        SharedInfo sh6 = new SharedInfo("Moments", R.mipmap.ic, "Sara", "$ 120");
        shrditms.add(sh6);

    }
}
