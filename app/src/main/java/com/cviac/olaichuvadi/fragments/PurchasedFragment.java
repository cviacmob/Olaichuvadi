package com.cviac.olaichuvadi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.PurchasedAdapter;
import com.cviac.olaichuvadi.datamodels.PurchasedInfo;

import java.util.ArrayList;
import java.util.List;

public class PurchasedFragment extends Fragment {

    GridView gv;
    List<PurchasedInfo> books;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_purchased, container, false);

        book_item();

        PurchasedAdapter adapter = new PurchasedAdapter(getActivity(), books);
        gv = (GridView) view.findViewById(R.id.purch_grid);
        gv.setAdapter(adapter);
        return view;
    }

    private void book_item() {

        books = new ArrayList<>();

        PurchasedInfo bo1 = new PurchasedInfo(R.mipmap.ic, "3 Mistakes of My Life", "Chetan Bhagat");
        books.add(bo1);

        PurchasedInfo bo2 = new PurchasedInfo(R.mipmap.ic, "Moments", "Author 1");
        books.add(bo2);

        PurchasedInfo bo3 = new PurchasedInfo(R.mipmap.ic, "Wings of Fire", "Dr. A.P.J.Abdul Kalam");
        books.add(bo3);

        PurchasedInfo bo4 = new PurchasedInfo(R.mipmap.ic, "Harry Potter", "J.K.Rowling");
        books.add(bo4);

        PurchasedInfo bo5 = new PurchasedInfo(R.mipmap.ic, "Book 2", "Author 2");
        books.add(bo5);
    }
}
