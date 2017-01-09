package com.cviac.olaichuvadi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.AuhtorsAdapter;
import com.cviac.olaichuvadi.datamodels.AuthorsInfo;

import java.util.ArrayList;
import java.util.List;

public class AuthorsFragment extends Fragment {

    GridView gv;
    List<AuthorsInfo> authr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_authors, container, false);

        shrd_item();

        AuhtorsAdapter adapter = new AuhtorsAdapter(getActivity(), authr);
        gv = (GridView) view.findViewById(R.id.authorsgrid);
        gv.setAdapter(adapter);
        return view;
    }

    private void shrd_item() {

        authr = new ArrayList<>();

        AuthorsInfo au1 = new AuthorsInfo("Chetan Bhagat", R.mipmap.ic);
        authr.add(au1);

        AuthorsInfo au2 = new AuthorsInfo("Sujatha", R.mipmap.ic);
        authr.add(au2);

        AuthorsInfo au3 = new AuthorsInfo("Gopinath", R.mipmap.ic);
        authr.add(au3);

        AuthorsInfo au4 = new AuthorsInfo("Jeyakantham", R.mipmap.ic);
        authr.add(au4);

        AuthorsInfo au5 = new AuthorsInfo("Rowling", R.mipmap.ic);
        authr.add(au5);

        AuthorsInfo au6 = new AuthorsInfo("Vairamuthu", R.mipmap.ic);
        authr.add(au6);
    }
}