package com.cviac.olaichuvadi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.FavouritesAdapter;
import com.cviac.olaichuvadi.datamodels.FavouritesInfo;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {

    GridView gv;
    List<FavouritesInfo> revv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_favourites, container, false);

        book_item();

        FavouritesAdapter adapter = new FavouritesAdapter(getActivity(), revv);
        gv = (GridView) view.findViewById(R.id.favgrid);
        gv.setAdapter(adapter);
        return view;
    }

    private void book_item() {

        revv = new ArrayList<>();

        FavouritesInfo bo1 = new FavouritesInfo(R.mipmap.ic, "3 Mistakes of My Life", "Chetan Bhagat");
        revv.add(bo1);

        FavouritesInfo bo2 = new FavouritesInfo(R.mipmap.ic, "Moments", "Author 1");
        revv.add(bo2);

        FavouritesInfo bo3 = new FavouritesInfo(R.mipmap.ic, "Wings of Fire", "Dr. Abdul Kalam");
        revv.add(bo3);

        FavouritesInfo bo4 = new FavouritesInfo(R.mipmap.ic, "Harry Potter", "J.K.Rowling");
        revv.add(bo4);

        FavouritesInfo bo5 = new FavouritesInfo(R.mipmap.ic, "Book 2", "Author 2");
        revv.add(bo5);
    }

}
