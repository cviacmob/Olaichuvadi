package com.cviac.olaichuvadi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.ReviewedAdapter;
import com.cviac.olaichuvadi.datamodels.ReviewedInfo;

import java.util.ArrayList;
import java.util.List;

public class ReviewedFragment extends Fragment {

    GridView gv;
    List<ReviewedInfo> rev;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_reviewed, container, false);

        book_item();

        ReviewedAdapter adapter = new ReviewedAdapter(getActivity(), rev);
        gv = (GridView) view.findViewById(R.id.revgrid);
        gv.setAdapter(adapter);
        return view;
    }

    private void book_item() {

        rev = new ArrayList<>();

        ReviewedInfo bo1 = new ReviewedInfo(R.mipmap.ic, "3 Mistakes of My Life", "Chetan Bhagat");
        rev.add(bo1);

        ReviewedInfo bo2 = new ReviewedInfo(R.mipmap.ic, "Harry Potter", "J.K.Rowling");
        rev.add(bo2);

    }

}
